package com.at.common

import android.app.Dialog
import android.view.Window
import com.melancholy.utils.BarUtils

/**
 * @Create_time: 2022/5/5 13:49
 * @Description:
 */

/**
 * 透明状态栏
 */
fun Dialog.immersiveStatusBar() {
    val window: Window = window ?: return
    BarUtils.immersiveStatusBar(window)
}

/**
 * 透明导航栏
 */
fun Dialog.immersiveNavigationBar() {
    val window: Window = window ?: return
    BarUtils.immersiveNavigationBar(window)
}

/**
 * 设置浅色状态栏背景（文字是否为深色）
 */
fun Dialog.setLightStatusBar(isLightingColor: Boolean) {
    val window: Window = window ?: return
    BarUtils.setLightStatusBar(window, isLightingColor)
}

/**
 * 设置浅色导航栏背景（文字是否为深色）
 */
fun Dialog.setLightNavigationBar(isLightingColor: Boolean) {
    val window: Window = window ?: return
    BarUtils.setLightNavigationBar(window, isLightingColor)
}

/**
 * 显示或隐藏状态栏和导航栏
 */
fun Dialog.showAndHideBar(isShow: Boolean) {
    val window: Window = window ?: return
    BarUtils.showAndHideBar(window, isShow)
}