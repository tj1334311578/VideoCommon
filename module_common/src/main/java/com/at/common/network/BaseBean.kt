package com.at.common.network

/**
 * @Create_time: 2021/12/6 20:34
 * @Author: wr
 * @Description: 基础类型
 */
data class BaseBean<T> (
    val code: Int,
    val msg: String?,
    val data: T? = null
) {
    fun success(): Boolean = code == 0
}