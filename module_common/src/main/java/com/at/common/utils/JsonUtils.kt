package com.at.common.utils

import com.squareup.moshi.Moshi
import java.lang.reflect.Type

/**
 * @Create_time: 2021/2/24 14:11

 * @Description: Json工具类
 */
object JsonUtils {
    @JvmStatic
    private val sInstance: Moshi by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Moshi.Builder()
            .build()
    }

    /**
     * 获取moshi实例
     */
    @JvmStatic
    fun getInstance(): Moshi = sInstance

    /**
     * 将对象转换为Json字符串
     * @param obj
     */
    @JvmStatic
    inline fun <reified T> toJson(obj: T): String {
        return getInstance().adapter(T::class.java).toJson(obj)
    }

    /**
     * 将Json字符串转换为对象
     * @param json
     */
    @JvmStatic
    inline fun <reified T> fromJson(json: String): T? {
        return getInstance().adapter(T::class.java).fromJson(json)
    }

    fun<T> fromJson(clazz: Class<T>, json: String): T? {
        return getInstance().adapter(clazz).fromJson(json)
    }

    fun<T> fromJson(type: Type, json: String): T? {
        return getInstance().adapter<T>(type).fromJson(json)
    }

}