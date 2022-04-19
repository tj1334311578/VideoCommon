package com.at.common.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 * @Create_time: 2022/4/12 15:37
 * @Description: 最基础的Fragment当内存不足时，Activity被回收，即便Fragment实例被保存，生命周期也会走一遍
 */

abstract class BaseFragment: Fragment {

    //数据是否加载过了
    private var mIsLoad: Boolean = false

    constructor(): super()

    constructor(contentLayoutId: Int): super(contentLayoutId)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                    savedInstanceState: Bundle?): View? {
        return createView(inflater, container, savedInstanceState)
    }

    /**
     * 创建View
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    protected open fun createView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
        return superOnCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 调用父容器创建View
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    protected fun superOnCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                    savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view, savedInstanceState)
        observer()
    }

    /**
     * 初始化
     */
    protected abstract fun initialize(view: View, savedInstanceState: Bundle?)

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
     * 查找View 可以为空
     * @param id
     */
    protected fun <T: View>findViewById(@IdRes id: Int): T? {
        return requireView().findViewById(id)
    }

    /**
     * 查找View 必须不为空
     * @param id
     */
    protected fun <T: View>requireViewById(@IdRes id: Int): T {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            requireView().requireViewById(id)
        } else {
            requireView().findViewById(id)!!
        }
    }
}