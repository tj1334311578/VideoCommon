package com.at.common.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.at.common.mvi.BaseMviFragment

/**
 *
 * @Description: java类作用描述
 * @CreateDate: 2022/6/30 下午3:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/30 下午3:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class TestFragment:Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}