package com.at.common.network

import android.util.MalformedJsonException
import androidx.annotation.StringRes
import com.at.common.R
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import org.json.JSONException
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Create_time: 2021/12/7 20:31
 * @Author: wr
 * @Description: 网络错误码
 */
internal object ErrorCode {
    //网络连接错误
    const val NETWORK_CONNECTION_ERROR: Int = 10000
    //无法解析该域名
    const val UNKNOWN_HOST_ERROR: Int = 10001
    //无网络连接
    const val SOCKET_ERROR: Int = 10002
    //连接网络超时
    const val SOCKET_TIMEOUT_ERROR: Int = 10003
    //json解析错误
    const val JSON_ERROR: Int = 10004
    //网络请求没有responseBody错误
    const val ERROR_HTTP_ERROR: Int = 10005
    //网络io流错误
    const val ERROR_HTTP_IO_ERROR: Int = 10006
    //未知错误
    const val UNKNOWN_ERROR: Int = 10007

    // 错误解析
    @JvmStatic
    fun parseError(t: Throwable): BaseBean<Any> {
        return when (t) {
            is ConnectException -> {
                // 连接网络失败
                // 飞行模式出现
                val message: String = getString(R.string.common_network_connection_error)
                BaseBean(NETWORK_CONNECTION_ERROR, message)
            }
            is UnknownHostException -> {
                //可能是网络关闭了
                // 关闭 wifi 和 网络流量抛出的异常
                val message: String = getString(R.string.common_unknown_host_error)
                BaseBean(UNKNOWN_HOST_ERROR, message)
            }
            is SocketException -> {
                val message: String = getString(R.string.common_socket_error)
                BaseBean(SOCKET_ERROR, message)
            }
            is SocketTimeoutException -> {
                //网络连接超时
                val message: String = getString(R.string.common_socket_timeout_error)
                BaseBean(SOCKET_TIMEOUT_ERROR, message)
            }
            is MalformedJsonException,
            is JsonDataException,
            is JsonEncodingException,
            is JSONException -> {
                // MalformedJsonException解析json格式不正确
                // JsonSyntaxException json解析错误 继承自JsonParseException
                //json解析错误
                val message: String = getString(R.string.common_json_error)
                BaseBean(JSON_ERROR, message)
            }
            is EOFException -> {
                //这种异常出现在返回无body的情况，但是一般是不可能出现不返回body
                val message: String = getString(R.string.common_http_error)
                BaseBean(ERROR_HTTP_ERROR, message)
            }
            is IOException -> {
                //这种错误很多
                val message: String = getString(R.string.common_http_io_error)
                BaseBean(ERROR_HTTP_IO_ERROR, message)
            }
            else -> {
                val message: String = getString(R.string.common_unknown_error)
                BaseBean(UNKNOWN_ERROR, message)
            }
        }
    }

    private fun getString(@StringRes id: Int): String {
        return ""
        //return AppInstance.getApplication().getString(id)
    }
}