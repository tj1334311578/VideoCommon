package com.at.common.global

import android.app.Application
import com.at.common.cache.CachePreferences
import com.melancholy.utils.AppInstance
import java.io.File

/**
 * @Create_time: 2020/9/17 15:03
 * @Description: 缓存工具
 */
object CacheHelper {
    @Volatile
    @JvmStatic
    private var sInstance: CachePreferences? = null

    @JvmStatic
    fun getInstance(): CachePreferences {
        if(sInstance == null) {
            synchronized(CacheHelper::class.java) {
                if(sInstance == null) {
                    val application: Application = AppInstance.getApplication()
                    val rootDir: String = File(application.filesDir, "mmkv").absolutePath
                    sInstance = CachePreferences(application, rootDir)
                }
            }
        }
        return sInstance!!
    }
}