package com.at.common.base

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.at.common.R
import com.at.common.immersiveStatusBar
import com.melancholy.widget.TitleBar

/**
 * @Create_time: 2022/4/12 15:36
 * @Description: 最基础的activity
 */

abstract class BaseActivity: AppCompatActivity, TitleBar.OnBackListener {
    //数据是否加载过了
    private var mIsLoad: Boolean = false
    private var mTitleBar: TitleBar? = null

    constructor(): super()

    constructor(contentLayoutId: Int): super(contentLayoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersiveBar()
        createView()
        initTitleBar()
        initialize(savedInstanceState)
        observer()
    }

    protected fun requireTitleBar(): TitleBar = mTitleBar!!

    protected fun getTitleBar(): TitleBar? = mTitleBar

    /**
     * 配置透明状态栏和导航栏
     */
    protected open fun immersiveBar() {
        immersiveStatusBar()
    }

    protected open fun createView() {}

    private fun initTitleBar() {
        mTitleBar = findViewById(R.id.title_bar)
        mTitleBar?.setOnBackListener(this)
    }

    /**
     * 初始化
     */
    protected abstract fun initialize(savedInstanceState: Bundle?)

    /**
     * 订阅数据
     */
    protected open fun observer() {

    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        if (isShouldLoadData() && !isLoad()) {
            loadData()
            mIsLoad = true
        }
    }

    /**
     * 是否加载过了
     */
    protected fun isLoad(): Boolean = mIsLoad

    /**
     * 是否需要加载数据
     */
    protected open fun isShouldLoadData(): Boolean = true

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    /**
     * 禁止字体缩放
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig.fontScale != 1.0f) {
            newConfig.setToDefaults()
        }
        super.onConfigurationChanged(newConfig)
    }

    @Synchronized
    override fun getResources(): Resources {
        val resources: Resources = super.getResources()
        val configuration = Configuration()
        configuration.setToDefaults()
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return resources
    }

    final override fun onBack() {
        if(!onBackClick()) {
            if(onBackPressedDispatcher.hasEnabledCallbacks()) {
                onBackPressedDispatcher.onBackPressed()
            } else {
                onBackPressed()
            }
        }
    }

    //返回true代表自己处理， false为系统处理
    protected open fun onBackClick(): Boolean = false

    final override fun onBackPressed() {
        if(!onBackPressedSupport()) {
            super.onBackPressed()
        }
    }

    //返回true代表自己处理， false为系统处理
    protected open fun onBackPressedSupport(): Boolean = false
}