package com.at.common.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.at.common.base.BaseFragment

/**
 * @Create_time: 2022/4/13 11:10
 * @Description:
 */

abstract class BaseBindingFragment<VIEW: ViewBinding>(): BaseFragment() {

    private var mBinding: VIEW? = null

    override fun createView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
        if(mBinding == null) {
            mBinding = createViewBinding(inflater, container, savedInstanceState)
        }
        return mBinding!!.root
    }

    protected abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?,
                                             savedInstanceState: Bundle?): VIEW

    protected fun getBinding(): VIEW? = mBinding

    protected fun requireBinding(): VIEW = mBinding!!

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}