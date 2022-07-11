package com.at.common.network

import com.squareup.moshi.JsonClass

/**
 * @Create_time: 2021/12/6 20:34
 * @Author: wr
 * @Description: 基础类型
 */
@JsonClass(generateAdapter = true)
data class BaseBean<T> (
    val code: Int = 0,
    val msg: String?,
    val data: T? = null
) {
    fun success(): Boolean = code == 0
}