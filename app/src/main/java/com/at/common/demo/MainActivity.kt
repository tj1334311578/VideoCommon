package com.at.common.demo

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.at.common.ImageConfig
import com.at.common.mvi.BaseMviActivity
import com.at.common.demo.databinding.ActivityMainBinding
import com.at.common.load
import com.at.common.utils.LoadingUtil
import com.at.common.utils.ToastUtils
import com.melancholy.network.ApiFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tv.danmaku.ijk.media.player.IjkLog
import tv.danmaku.ijk.media.player.IjkMediaPlayer

@AndroidEntryPoint
class MainActivity: BaseMviActivity<ActivityMainBinding, MainViewModel>() {

    override fun initialize(savedInstanceState: Bundle?) {
        requireTitleBar().addBackButton()
        getViewModel().print()
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.print()
    }

    override fun loadData() {
        val ijkMediaPlayer: IjkMediaPlayer? = null
        lifecycleScope.launch(Dispatchers.IO) {
            val drawable = load(ImageConfig("https://img0.baidu.com/it/u=2862534777,914942650&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"))
            withContext(Dispatchers.Main) {
                requireBinding().ivImage.setImageDrawable(drawable)
            }
        }


        /*requireBinding().ivImage.load(config = ImageConfig("https://img0.baidu.com/it/u=2862534777,914942650&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        placeholderId = R.drawable.ic_launcher_foreground, errorId = R.drawable.ic_launcher_background))*/
        ToastUtils.show("开始加载数据")
        lifecycleScope.launch(Dispatchers.IO) {
            ApiFactory.getInstance().create(ApiService::class.java)
                .baidu("https://www.baidu.com")
        }
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onBackPressedSupport(): Boolean {
        LoadingUtil.showLoading(this)
        return true
    }

}