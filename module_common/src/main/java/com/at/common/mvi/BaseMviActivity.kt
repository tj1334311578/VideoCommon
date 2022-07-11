package com.at.common.mvi

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.at.common.binding.BaseBindingActivity

/**
 * @Create_time: 2022/4/14 17:17
 * @Description:
 */

abstract class BaseMviActivity<VIEW: ViewBinding, VM: BaseAndroidViewModel<out IUiState,
        out IUiEvent, out IUiEffect>>: BaseBindingActivity<VIEW>() {

    private val mViewModel by lazy {
        val provider = ViewModelProvider(getViewModelStoreOwner())
        val key: String? = getViewModelKey()
        if(key.isNullOrEmpty()) {
            provider[getViewModelClass()]
        } else {
            provider[key, getViewModelClass()]
        }
    }

    protected fun getViewModel(): VM = mViewModel

    protected open fun getViewModelStoreOwner(): ViewModelStoreOwner = this

    protected abstract fun getViewModelClass(): Class<VM>

    protected open fun getViewModelKey(): String? = null

    override fun observer() {

    }
}