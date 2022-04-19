package com.at.videoplayer

import android.util.Log
import javax.inject.Inject

/**
 * @Create_time: 2022/4/14 16:50
 * @Description:
 */

class TestObj @Inject constructor(private val test2: Test2) {

    fun test() {
        Log.d("TAG1234560", "test")
        test2.test()
    }
}