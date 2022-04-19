package com.at.common.status

/**
 * @Create_time: 2022/4/12 13:44
 * @Description: 刷新状态
 */

sealed class RefreshStatus {
    //空闲状态
    object RefreshIdle: RefreshStatus()
    //刷新中
    object RefreshLoading: RefreshStatus()
    //刷新成功
    object RefreshSuccess: RefreshStatus()
    //刷新失败
    data class RefreshFail(val error: Throwable): RefreshStatus()
}