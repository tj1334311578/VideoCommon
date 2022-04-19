package com.at.common

import android.app.Application
import com.at.common.global.Constants
import com.at.common.network.adapter.BaseBeanCallAdapterFactory
import com.at.common.network.convertor.MoshiConverterFactory
import com.at.common.utils.ToastUtils
import com.melancholy.application.lifecycle.IApplicationLifecycle
import com.melancholy.network.ApiFactory
import com.melancholy.network.Config
import com.melancholy.process.monitor.ProcessMonitor
import com.melancholy.register.annotation.RegisterService

/**
 * @Create_time: 2022/4/19 13:36
 * @Description: 通用基础库初始化
 */

@RegisterService(serviceClass = IApplicationLifecycle::class)
class CommonApplication: IApplicationLifecycle {
    override fun priority(): Int = 0

    override fun onCreate(application: Application) {
        //初始化Toast框架
        ToastUtils.init(application)
        //初始化路由框架
       /* if(Constants.IS_DEBUG) {
            Router.openDebug()
            Router.openLog()
        }
        Router.init(application)*/
        val config = Config(Constants.BASE_URL, true, capacity = 32,
            listOf(MoshiConverterFactory.create()),
            listOf(BaseBeanCallAdapterFactory()))
        ApiFactory.init(config)
        //ImageLoader.init(this)
        ProcessMonitor.init(application)
    }
}