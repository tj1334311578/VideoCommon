package com.at.common.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * @Create_time: 2020/4/21 17:59
 * @Author: wr
 * @Description: xml存储 缓存框架的隔离
 * 替换sp，速度更快，还可以加密缓存 支持多进程
 */
class CachePreferences: CacheListener {

    private val mCache: CacheListener

    /**
     * 初始化换成框架
     * @param context
     * @param rootDir 存储路径
     * @param isMode 是否是多进程 默认为单进程
     * @param cryptKey　加密的AES秘钥　默认为null
     * @param isDebug　是否是debug模式　默认为release模式
     */
    constructor(context: Context, rootDir: String? = null, isMode: Boolean = false,
                cryptKey: String? = null, isDebug: Boolean = false) {
        mCache = MMKVPreferences(context, rootDir, isMode, cryptKey, isDebug)
    }

    override fun contains(key: String?): Boolean {
        return mCache.contains(key)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return mCache.getBoolean(key, defValue)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        mCache.unregisterOnSharedPreferenceChangeListener(listener)
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return mCache.getInt(key, defValue)
    }

    override fun getAll(): MutableMap<String, *> {
        return mCache.all
    }

    override fun edit(): SharedPreferences.Editor {
        return mCache.edit()
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return mCache.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return mCache.getFloat(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return mCache.getStringSet(key, defValues)
    }

    override fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        mCache.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun getString(key: String?, defValue: String?): String? {
        return mCache.getString(key, defValue)
    }

    override fun clear(): SharedPreferences.Editor {
        return mCache.clear()
    }

    override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
        return mCache.putLong(key, value)
    }

    override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
        return mCache.putInt(key, value)
    }

    override fun remove(key: String?): SharedPreferences.Editor {
        return mCache.remove(key)
    }

    override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
        return mCache.putBoolean(key, value)
    }

    override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
        return mCache.putStringSet(key, values)
    }

    override fun commit(): Boolean {
        return mCache.commit()
    }

    override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
        return mCache.putFloat(key, value)
    }

    override fun apply() {
        mCache.apply()
    }

    override fun putString(key: String?, value: String?): SharedPreferences.Editor {
        return mCache.putString(key, value)
    }
}