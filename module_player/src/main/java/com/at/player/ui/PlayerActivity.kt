package com.at.player.ui

import android.app.Activity
import android.os.Bundle
import com.at.player.R
import com.at.player.custom.PlayerView

/**
 * @Create_time: 2022/4/18 17:21
 * @Description:
 */

class PlayerActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val playerView: PlayerView = findViewById(R.id.pv_player)
        playerView.setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "测试视频")
        //http://stream2.ahtv.cn/jjsh/cd/live.m3u8
        //playerView.setUp("http://stream2.ahtv.cn/jjsh/cd/live.m3u8", "")
        //val mJzDataSource = JZDataSource("http://stream2.ahtv.cn/jjsh/cd/live.m3u8")
        //playerView.setUp(mJzDataSource, JzvdStd.SCREEN_NORMAL, JZMediaSystem::class.java)
        playerView.startVideo()
    }
}