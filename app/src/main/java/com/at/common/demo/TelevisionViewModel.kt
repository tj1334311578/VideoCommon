package com.at.common.demo

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.at.common.mvi.BaseAndroidViewModel
import com.at.common.mvi.IUiEffect
import com.at.common.mvi.IUiEvent
import com.at.common.mvi.IUiState
import com.at.common.status.PageStatus
import com.at.common.status.RefreshStatus
import com.melancholy.network.ApiFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @Description: WorkViewModel
 * @CreateDate: 2022/6/13 下午2:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/13 下午2:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

data class TelevisionUiState(
    val pageStatus: PageStatus = PageStatus.Loading,
    val refreshStatus: RefreshStatus = RefreshStatus.RefreshIdle,
): IUiState

sealed class TelevisionUiEvent: IUiEvent {
    data class HeaderData(val id: Int, val categoryId: Int): TelevisionUiEvent()
    object InitLoadData: TelevisionUiEvent()
    object RefreshData: TelevisionUiEvent()
}
sealed class TelevisionUiEffect: IUiEffect {

}

@HiltViewModel
class TelevisionViewModel @Inject constructor(application: Application) :
    BaseAndroidViewModel<TelevisionUiState, TelevisionUiEvent, TelevisionUiEffect>(application) {
    private var mId: Int = -1
    private var mCategoryId: Int = -1

    override fun providerInitialState(): TelevisionUiState {
        return TelevisionUiState()
    }

    override fun handleEvent(event: TelevisionUiEvent) {
        when(event){
            is TelevisionUiEvent.HeaderData -> {
                mId = event.id
                mCategoryId = event.categoryId
            }
            is TelevisionUiEvent.InitLoadData -> initLoadData()
            is TelevisionUiEvent.RefreshData -> refreshData()
        }
    }

    private fun initLoadData() {

    }

    private fun refreshData() {
        setState { copy(refreshStatus=RefreshStatus.RefreshLoading )}

        viewModelScope.launch {
            delay(1000)
            setState { copy(refreshStatus=RefreshStatus.RefreshSuccess )}
        }

    }

}