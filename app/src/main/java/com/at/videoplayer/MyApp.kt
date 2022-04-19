package com.at.videoplayer

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig.fontScale != 1.0f) {
            newConfig.setToDefaults()
        }
        super.onConfigurationChanged(newConfig)
    }
}