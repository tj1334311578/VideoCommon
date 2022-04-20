package com.at.common.cache

import android.content.Context
import android.content.SharedPreferences
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

/**
 * @Create_time: 2020/5/11 14:17
 * @Author: wr
 * @Description: MMKV　实现类，用来隔离框架
 */
internal class MMKVPreferences: CacheListener {
    private val mMMKV: MMKV

    constructor(context: Context, rootDir: String? = null, mode: Boolean = false,
                cryptKey: String? = null, isDebug: Boolean = false) {
        val logLevel = if(isDebug) MMKVLogLevel.LevelDebug else MMKVLogLevel.LevelNone
        if(rootDir != null) {
            MMKV.initialize(rootDir, logLevel)
        } else {
            MMKV.initialize(context, logLevel)
        }
        mMMKV = MMKV.defaultMMKV(if(mode) MMKV.MULTI_PROCESS_MODE else MMKV.SINGLE_PROCESS_MODE,
            cryptKey)
    }

    override fun contains(key: String?): Boolean {
        return mMMKV.containsKey(key)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return mMMKV.getBoolean(key, defValue)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        mMMKV.unregisterOnSharedPreferenceChangeListener(listener)
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return mMMKV.getInt(key, defValue)
    }

    override fun getAll(): MutableMap<String, *> {
        return mMMKV.all
    }

    override fun edit(): SharedPreferences.Editor {
        return mMMKV.edit()
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return mMMKV.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return mMMKV.getFloat(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return mMMKV.getStringSet(key, defValues)
    }

    override fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        mMMKV.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun getString(key: String?, defValue: String?): String? {
        return mMMKV.getString(key, defValue)
    }

    override fun clear(): SharedPreferences.Editor {
        return mMMKV.clear()
    }

    override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
        return mMMKV.putLong(key, value)
    }

    override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
        return mMMKV.putInt(key, value)
    }

    override fun remove(key: String?): SharedPreferences.Editor {
        return mMMKV.remove(key)
    }

    override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
        return mMMKV.putBoolean(key, value)
    }

    override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
        return mMMKV.putStringSet(key, values)
    }

    override fun commit(): Boolean {
        return mMMKV.commit()
    }

    override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
        return mMMKV.putFloat(key, value)
    }

    override fun apply() {
        return mMMKV.apply()
    }

    override fun putString(key: String?, value: String?): SharedPreferences.Editor {
        return mMMKV.putString(key, value)
    }
}