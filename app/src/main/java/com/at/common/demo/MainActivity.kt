package com.at.common.demo

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.at.common.mvi.BaseMviActivity
import com.at.common.demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseMviActivity<ActivityMainBinding, MainViewModel>() {

    override fun initialize(savedInstanceState: Bundle?) {
        getViewModel().print()
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.print()
    }

    override fun loadData() {
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}