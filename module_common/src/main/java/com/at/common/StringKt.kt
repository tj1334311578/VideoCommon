package com.at.common

import com.at.common.global.Constants

/**
 * @Create_time: 2022/5/23 15:54
 * @Description: String扩展方法
 */

/**
 * 返回真实的url图片地址
 */
fun String?.imageUrl(): String? {
    return if(this?.startsWith("http") == true) {
        this
    } else if(this == null) {
        this
    } else {
        if(this.startsWith("/")) {
            "${Constants.BASE_URL}${this.substring(1)}"
        } else {
            "${Constants.BASE_URL}$this"
        }
    }
}