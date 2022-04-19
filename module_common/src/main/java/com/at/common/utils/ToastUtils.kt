package com.at.common.utils

import android.app.Application
import androidx.annotation.StringRes
import com.hjq.toast.ToastUtils

/**
 * @Create_time: 2022/4/14 14:12
 * @Description: toast工具类
 */

object ToastUtils {

    @JvmStatic
    internal fun init(application: Application) {
        ToastUtils.init(application)
    }

    // 显示 Toast
    @JvmStatic
    fun show(@StringRes resId: Int) {
        ToastUtils.show(resId)
    }

    @JvmStatic
    fun show(text: CharSequence?) {
        ToastUtils.show(text)
    }

    // debug 模式下显示 Toast
    @JvmStatic
    fun debugShow(@StringRes resId: Int) {
        ToastUtils.debugShow(resId)
    }

    @JvmStatic
    fun debugShow(text: CharSequence?) {
        ToastUtils.debugShow(text)
    }

    @JvmStatic
    fun cancel() {
        ToastUtils.cancel()
    }

}