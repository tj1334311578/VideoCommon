package com.at.common.network.adapter

import com.at.common.network.BaseBean
import com.at.common.network.ErrorCode
import okhttp3.Request
import org.json.JSONObject
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @Create_time: 2021/12/6 21:07
 * @Author: wr
 * @Description: BaseBean转换器,加处理异常
 */
class BaseBeanCallAdapterFactory: CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>,
                     retrofit: Retrofit): CallAdapter<*, *>? {
        if(getRawType(returnType) != Call::class.java) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalArgumentException("Call return type must be parameterized as Call<Foo> " +
                    "or Call<? extends Foo>")
        }
        val responseType: Type = getParameterUpperBound(0, returnType)
        if(getRawType(responseType) == BaseBean::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalArgumentException("Call return type must be parameterized as" +
                        " Call<BaseBean<Foo>> or Call<BaseBean<? extends Foo>>")
            }
            return BaseBeanCallAdapter(responseType)
        }
        return null
    }

    private class BaseBeanCallAdapter constructor(private val responseType: Type):
        CallAdapter<BaseBean<Any>, Call<*>> {
        override fun responseType(): Type = responseType

        override fun adapt(call: Call<BaseBean<Any>>): Call<*> = BaseBeanCallbackCall(call)
    }

    private class BaseBeanCallbackCall<T>(val delegate: Call<T>): Call<T> {

        @Suppress("UNCHECKED_CAST")
        override fun enqueue(callback: Callback<T>) {

            delegate.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body: BaseBean<Any> = if (delegate.isCanceled) {
                        BaseBean(-1, "Canceled")
                    } else {
                        if(response.isSuccessful && response.body() != null) {
                            //这里是成功回调
                            response.body() as BaseBean<Any>
                        } else {
                            //这里都是okHttp网络请求的错误
                            val errorJson: String? = response.errorBody()?.string()
                            val msg: String = if(errorJson.isNullOrEmpty()) {
                                response.message()
                            } else {
                                try {
                                    JSONObject(errorJson).getString("msg")
                                } catch (e: Exception) {
                                    response.message()
                                }
                            }
                            BaseBean(response.code(), msg)
                        }
                    }
                    val realResponse: Response<T> = Response.success(body as T)
                    callback.onResponse(this@BaseBeanCallbackCall, realResponse)
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    //解析错误
                    val response: Response<T> = Response.success(ErrorCode.parseError(t) as T)
                    callback.onResponse(this@BaseBeanCallbackCall, response)
                    t.printStackTrace()
                }
            })
        }

        override fun isExecuted(): Boolean {
            return delegate.isExecuted
        }

        @Throws(IOException::class)
        override fun execute(): Response<T> {
            return delegate.execute()
        }

        override fun cancel() {
            delegate.cancel()
        }

        override fun isCanceled(): Boolean {
            return delegate.isCanceled
        }

        override fun clone(): Call<T> {
            return BaseBeanCallbackCall(delegate.clone())
        }

        override fun request(): Request {
            return delegate.request()
        }
    }
}