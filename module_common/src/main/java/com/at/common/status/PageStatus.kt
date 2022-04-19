package com.at.common.status

/**
 * @Create_time: 2022/4/12 13:43
 * @Description: 页面状态
 */

sealed class PageStatus {
    //空页面
    object Empty: PageStatus()
    //加载页面
    object Loading: PageStatus()
    //加载成功页面
    object Success: PageStatus()
    //加载失败页面
    data class Error(val error: Throwable): PageStatus()
}