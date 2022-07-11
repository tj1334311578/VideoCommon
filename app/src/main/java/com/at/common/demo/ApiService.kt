package com.at.common.demo

import com.at.common.network.BaseBean
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * @Create_time: 2022/4/20 12:23
 * @Description:
 */

interface ApiService {

    @GET
    suspend fun baidu(@Url url: String): BaseBean<Any>
}