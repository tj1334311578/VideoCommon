package com.at.common.network.interceptor

import com.melancholy.network.INetworkInterceptor
import com.melancholy.register.annotation.RegisterService
import okhttp3.Interceptor.Chain
import okhttp3.Response

/**
 * @Create_time: 2022/4/14 14:24
 * @Description: 通用网络拦截器
 */

@RegisterService(serviceClass = INetworkInterceptor::class)
class CommonInterceptor: INetworkInterceptor {

    override fun priority(): Int = 0

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
    }
}