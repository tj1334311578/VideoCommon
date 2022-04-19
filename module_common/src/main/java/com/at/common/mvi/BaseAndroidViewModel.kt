package com.at.common.mvi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @Create_time: 2022/4/12 19:23
 * @Description: MviViewModel基类
 */

abstract class BaseAndroidViewModel<STATE: IUiState, EVENT: IUiEvent, EFFECT: IUiEffect>
    (application: Application): AndroidViewModel(application) {

    /**
     * 界面初始化状态
     */
    private val _initialSate: STATE by lazy { providerInitialState() }

    /**
     * 包含UI所有的状态
     * SateFlow和携程结合使用可以达到LiveData的效果
     */
    private val _state = MutableStateFlow(_initialSate)
    val state: StateFlow<STATE> = _state.asStateFlow()

    /**
     * 接收用户意图
     * SharedFlow特点
     * 1.数据会以流的形式发送，不会丢失，新事件不会覆盖旧事件
     * 2.数据不是粘性的，消费一次就不会再次出现
     * 3.无法接收到collect之前的事件
     */
    private val _event = MutableSharedFlow<EVENT>()
    private val event: SharedFlow<EVENT> = _event.asSharedFlow()

    /**
     * 事件带来的副作用，通常是一次性事件且 一对一的订阅关系
     * 例如：弹Toast、导航Fragment等
     * 1.每个消息只有一个订阅者可以收到，用于一对一的通信
     * 2.第一个订阅者可以收到collect之前的事件
     */
    private val _effect: Channel<EFFECT> = Channel()
    val effect: Flow<EFFECT> = _effect.receiveAsFlow()

    /**
     * 处理初始化界面
     */
    abstract fun providerInitialState(): STATE

    init {
        subscribeEvents()
    }

    /**
     * 订阅事件
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * 处理事件
     */
    abstract fun handleEvent(event: EVENT)

    /**
     * 发送意图
     */
    fun sendEvent(event: EVENT) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    /**
     * 发送状态
     */
    protected fun setState(reduce: STATE.() -> STATE) {
        val newSate: STATE = _state.value.reduce()
        _state.value = newSate
    }

    /**
     * 发送副作用
     */
    protected fun setEffect(builder: () -> EFFECT) {
        val newEffect: EFFECT = builder()
        viewModelScope.launch {
            _effect.send(newEffect)
        }
    }
}