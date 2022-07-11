package com.at.common.demo

import android.app.Application
import com.at.common.mvi.BaseAndroidViewModel
import com.at.common.mvi.IUiEffect
import com.at.common.mvi.IUiEvent
import com.at.common.mvi.IUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Create_time: 2022/4/14 16:48
 * @Description:
 */

data class MainUiState(
    val msg: String
): IUiState

sealed class MainUiEvent: IUiEvent {

}

sealed class MainUiEffect: IUiEffect {

}


@HiltViewModel
class MainViewModel @Inject constructor(application: Application, private val test: Test2):
    BaseAndroidViewModel<MainUiState, MainUiEvent, MainUiEffect>(application) {

    fun print() {
        test.test()
    }

    override fun providerInitialState(): MainUiState {
        return MainUiState("")
    }

    override fun handleEvent(event: MainUiEvent) {

    }

}