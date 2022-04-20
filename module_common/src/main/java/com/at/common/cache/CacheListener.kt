package com.at.common.cache

import android.content.SharedPreferences

/**
 * @Create_time: 2020/5/11 14:17
 * @Author: wr
 * @Description: 缓存接口
 */
internal interface CacheListener: SharedPreferences, SharedPreferences.Editor {
}