package com.at.common.base

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment

/**
 * @Create_time: 2022/4/12 15:42
 * @Description: 最基础的DialogFragment
 */

abstract class BaseDialogFragment: DialogFragment() {
    //数据是否加载过了
    private var mIsLoad: Boolean = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            setStyle(getCustomStyle(), getCustomTheme())
        }
    }

    /**
     * 获取style
     */
    protected open fun getCustomStyle(): Int = STYLE_NORMAL

    /**
     * 获取theme
     */
    @StyleRes
    protected open fun getCustomTheme(): Int = 0

    /**
     * 该方法在onCreateView之前调用 此处可以实现自己的dialog
     * 注意这里只创建了dialog，没有设置View
     * @param savedInstanceState
     */
    final override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return createDialog(savedInstanceState)
    }

    /**
     * 创建Dialog
     * @param savedInstanceState
     */
    protected open fun createDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    /**
     * 创建View，将View设置到dialog之上
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
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
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
        initialize(view, savedInstanceState)
        observer()
    }

    /**
     * 设置dialog的属性
     */
    private fun initDialog() {
        val dialog: Dialog = dialog!!
        //是否点击dialog外部隐藏dialog
        dialog.setCanceledOnTouchOutside(getCanceledOnTouchOutside())
        //是否点击返回键隐藏dialog
        dialog.setCancelable(getCancelable())
        val window: Window = dialog.window!!
        //设置dialog的大小
        window.setLayout(getWidth(), getHeight())
        //设置dialog显示的位置
        window.setGravity(getGravity())
        //设置dialog显示和隐藏动画
        window.setWindowAnimations(getWindowAnimations())
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

    /**
     * 获取宽度
     */
    protected open fun getWidth(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * 获取高度
     */
    protected open fun getHeight(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * 获取dialog显示的相对位置
     */
    protected open fun getGravity(): Int = Gravity.CENTER

    /**
     * 是否点击返回键隐藏dialog
     */
    protected open fun getCancelable(): Boolean = true

    /**
     * 是否点击dialog外部隐藏dialog
     */
    protected open fun getCanceledOnTouchOutside(): Boolean = true

    /**
     * 设置dialog显示和隐藏动画
     */
    @StyleRes
    protected open fun getWindowAnimations(): Int = 0

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