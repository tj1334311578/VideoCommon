package com.at.common

import android.content.Context
import android.widget.ImageView
import coil.imageLoader
import coil.loadAny
import coil.request.ImageRequest

/**
 * @Create_time: 2022/4/19 15:16
 * @Description: 扩展方法
 */

/**
 * 加载图片
 * @param url
 */
fun ImageView.load(url: Any?) {
    this.loadAny(url) {
        //placeholder()
        //error()
    }
}

/**
 *
 */
suspend fun aa(context: Context, url: Any?, width: Int, height: Int) {
    val request = ImageRequest.Builder(context)
        .data(url)
        .size(width, height)
        .build()
    val drawable = context.imageLoader.execute(request).drawable
}

