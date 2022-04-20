package com.at.common

import android.app.Application
import android.content.res.Configuration
import com.melancholy.application.lifecycle.ApplicationLifecycle

/**
 * @Create_time: 2022/4/12 11:05
 * @Description: baseApplication
 */
open class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationLifecycle.instance.onCreate(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ApplicationLifecycle.instance.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
        ApplicationLifecycle.instance.onTerminate()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        ApplicationLifecycle.instance.onTrimMemory(level)
    }

    /**
     * 禁止字体缩放
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig.fontScale != 1.0f) {
            newConfig.setToDefaults()
        }
        super.onConfigurationChanged(newConfig)
    }

}