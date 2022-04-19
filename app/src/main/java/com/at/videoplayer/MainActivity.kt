package com.at.videoplayer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.at.common.binding.BaseBindingActivity
import com.at.common.mvi.BaseMviActivity
import com.at.videoplayer.databinding.ActivityMainBinding
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