package com.at.common.network

import com.squareup.moshi.JsonClass

/**
 * @Create_time: 2022/5/23 19:11
 * @Description:
 */

@JsonClass(generateAdapter = true)
data class PageBean<T> (
    val list: List<T>?,
    val count: Int = 0,
    val total: Int = 0,
)