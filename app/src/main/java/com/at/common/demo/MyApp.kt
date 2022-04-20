package com.at.common.demo

import android.content.res.Configuration
import com.at.common.BaseApplication
import com.at.common.global.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: BaseApplication() {

    override fun onCreate() {
        Constants.BASE_URL = "https://www.baidu.com"
        Constants.IS_DEBUG = BuildConfig.DEBUG
        super.onCreate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig.fontScale != 1.0f) {
            newConfig.setToDefaults()
        }
        super.onConfigurationChanged(newConfig)
    }
}