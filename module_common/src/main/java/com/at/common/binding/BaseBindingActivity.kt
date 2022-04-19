package com.at.common.binding

import androidx.viewbinding.ViewBinding
import com.at.common.base.BaseActivity

/**
 * @Create_time: 2022/4/13 11:11
 * @Description:
 */

abstract class BaseBindingActivity<VIEW: ViewBinding>(): BaseActivity() {

    private var mBinding: VIEW? = null

    final override fun createView() {
        mBinding = createViewBinding()
    }

    protected abstract fun createViewBinding(): VIEW

    protected fun getBinding(): VIEW? = mBinding

    protected fun requireBinding(): VIEW = mBinding!!

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}