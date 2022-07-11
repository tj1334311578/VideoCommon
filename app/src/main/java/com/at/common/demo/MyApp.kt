package com.at.common.demo

import android.content.res.Configuration
import com.at.common.BaseApplication
import com.at.common.global.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: BaseApplication() {

    override fun onCreate() {
//        Constants.BASE_URL = "https://api.luofeiyu.tech,http://192.168.2.71:8000,http://192.168.2.103:8003,http://192.168.2.201:10002"
        Constants.BASE_URL = "https://api.luofeiyu.tech,http://192.168.2.197:10070,http://192.168.2.71:8000,http://192.168.2.103:8003"
        Constants.URL_PATH_SUFFIX = "api/v1/"
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