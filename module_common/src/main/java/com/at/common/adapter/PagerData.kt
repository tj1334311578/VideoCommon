package com.at.common.adapter

import android.os.Bundle

/**
 * @Create_time: 2022/4/28 09:57
 * @Description:
 */

data class PagerData (
    val path: String,
    val itemId: Long,
    val args: Bundle? = null
)