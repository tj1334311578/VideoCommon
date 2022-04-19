package com.at.common.status

/**
 * @Create_time: 2022/4/12 13:43
 * @Description: 加载状态
 */

sealed class LoadMoreStatus {
    //空闲状态
    object LoadMoreIdle: LoadMoreStatus()
    //加载中
    object LoadMoreLoading: LoadMoreStatus()
    //加载成功
    data class LoadMoreSuccess(val hasMore: Boolean): LoadMoreStatus()
    //加载失败
    data class LoadMoreFail(val error: Throwable): LoadMoreStatus()
}