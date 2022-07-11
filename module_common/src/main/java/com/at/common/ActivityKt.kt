package com.at.common

import android.app.Activity
import com.melancholy.utils.BarUtils

/**
 * @Create_time: 2022/5/5 13:42
 * @Description:
 */

/**
 * 透明状态栏
 */
fun Activity.immersiveStatusBar() {
    BarUtils.immersiveStatusBar(window)
}

/**
 * 透明导航栏
 */
fun Activity.immersiveNavigationBar() {
    BarUtils.immersiveNavigationBar(window)
}

/**
 * 设置浅色状态栏背景（文字是否为深色）
 */
fun Activity.setLightStatusBar(isLightingColor: Boolean) {
    BarUtils.setLightStatusBar(window, isLightingColor)
}

/**
 * 设置浅色导航栏背景（文字是否为深色）
 */
fun Activity.setLightNavigationBar(isLightingColor: Boolean) {
    BarUtils.setLightNavigationBar(window, isLightingColor)
}

/**
 * 显示或隐藏状态栏和导航栏
 */
fun Activity.showAndHideBar(isShow: Boolean) {
    BarUtils.showAndHideBar(window, isShow)
}