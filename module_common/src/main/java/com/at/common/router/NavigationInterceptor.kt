package com.at.common.router

import android.content.Context
import com.melancholy.process.monitor.ProcessMonitor
import com.melancholy.process.monitor.ProcessMonitorListener
import com.melancholy.router.annotation.annotation.Interceptor
import com.melancholy.router.api.facade.Postcard
import com.melancholy.router.api.facade.callback.InterceptorCallback
import com.melancholy.router.api.facade.template.IInterceptor
import com.melancholy.utils.AppUtils

/**
 * @Create_time: 2022/4/27 10:53
 * @Description: 判断后台跳转不进行跳转
 */

@Interceptor(priority = 0, name = "跳转拦截器")
class NavigationInterceptor: IInterceptor {
    private val mModify: Object = Object()

    private lateinit var mContext: Context

    override fun init(context: Context) {
        mContext = context
    }

    private fun notifyWait() {
        synchronized(mModify) {
            mModify.notify()
        }
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        if(!AppUtils.isRunningForeground(mContext)) {
            ProcessMonitor.get().addAppStatusListener(object: ProcessMonitorListener {

                override fun onForeground() {
                    ProcessMonitor.get().removeAppStatusListener(this)
                    notifyWait()
                }

            })
            synchronized(mModify) {
                mModify.wait()
            }
        }
        callback.onContinue(postcard)
    }
}