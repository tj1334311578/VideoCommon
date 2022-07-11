package com.at.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import coil.imageLoader
import coil.loadAny
import coil.request.CachePolicy
import coil.request.ImageRequest

/**
 * @Create_time: 2022/4/19 15:16
 * @Description: 扩展方法
 */

//可读可写
const val CACHE_ENABLED: Int = 0
//只读
const val CACHE_READ_ONLY: Int = 1
//只写
const val CACHE_WRITE_ONLY: Int = 2
//不可读不可写
const val CACHE_DISABLED: Int = 3

@IntDef(value = [CACHE_ENABLED, CACHE_READ_ONLY, CACHE_WRITE_ONLY, CACHE_DISABLED])
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class ImageCacheMode

/**
 * 加载图片的配置
 */
data class ImageConfig(
    //加载图片
    val url: Any?,
    //加载图片的宽高
    @IntRange(from = -1L)
    val width: Int = -1,
    @IntRange(from = -1L)
    val height: Int = -1,
    //占位图
    val placeholder: Drawable? = null,
    @DrawableRes val placeholderId: Int = 0,
    //错误占位图
    val error: Drawable? = null,
    @DrawableRes val errorId: Int = 0,
    //内存缓存模式
    @ImageCacheMode val memoryCacheMode: Int = CACHE_ENABLED,
    //磁盘缓存模式
    @ImageCacheMode val diskCacheMode: Int = CACHE_ENABLED
)

/**
 * 获取缓存模式
 * @param mode
 */
private fun getCacheMode(mode: Int): CachePolicy {
    return when(mode) {
        CACHE_ENABLED -> CachePolicy.ENABLED
        CACHE_READ_ONLY -> CachePolicy.READ_ONLY
        CACHE_WRITE_ONLY -> CachePolicy.WRITE_ONLY
        else -> CachePolicy.DISABLED
    }
}

/**
 * 加载图片
 * @param config
 */
fun ImageView.load(config: ImageConfig) {
    loadAny(config.url) {
        if(config.width != -1 && config.height != -1) {
            size(config.width, config.height)
        }
        if (config.placeholder != null) {
            placeholder(config.placeholder)
        }
        if (config.placeholderId != 0) {
            placeholder(config.placeholderId)
        }
        if (config.error != null) {
            error(config.error)
        }
        if (config.errorId != 0) {
            error(config.errorId)
        }
        memoryCachePolicy(getCacheMode(config.memoryCacheMode))
        diskCachePolicy(getCacheMode(config.diskCacheMode))
    }
}

/**
 * 通过回调加载图片
 */
fun Context.load(config: ImageConfig, onSuccess: (result: Drawable) -> Unit,
                 onStart: (placeholder: Drawable?) -> Unit = {},
                 onError: (error: Drawable?) -> Unit = {}) {
    val builder: ImageRequest.Builder = ImageRequest.Builder(this)
    if(config.width != -1 && config.height != -1) {
        builder.size(config.width, config.height)
    }
    if (config.placeholder != null) {
        builder.placeholder(config.placeholder)
    }
    if (config.placeholderId != 0) {
        builder.placeholder(config.placeholderId)
    }
    if (config.error != null) {
        builder.error(config.error)
    }
    if (config.errorId != 0) {
        builder.error(config.errorId)
    }
    builder.memoryCachePolicy(getCacheMode(config.memoryCacheMode))
        .data(config.url)
        .diskCachePolicy(getCacheMode(config.diskCacheMode))
        .target(
            onStart = { placeholder ->
                onStart(placeholder)
            },
            onSuccess = { result ->  
                onSuccess(result)
            },
            onError = { error ->
                onError(error)
            }
        )
        .build()
}

/**
 * 后台线程获取图片
 */
suspend fun Context.load(config: ImageConfig): Drawable? {
    val builder: ImageRequest.Builder = ImageRequest.Builder(this)
    if(config.width != -1 && config.height != -1) {
        builder.size(config.width, config.height)
    }
    if (config.placeholder != null) {
        builder.placeholder(config.placeholder)
    }
    if (config.placeholderId != 0) {
        builder.placeholder(config.placeholderId)
    }
    if (config.error != null) {
        builder.error(config.error)
    }
    if (config.errorId != 0) {
        builder.error(config.errorId)
    }
    val request: ImageRequest = builder.memoryCachePolicy(getCacheMode(config.memoryCacheMode))
        .data(config.url)
        .diskCachePolicy(getCacheMode(config.diskCacheMode))
        .build()
    return imageLoader.execute(request).drawable
}

