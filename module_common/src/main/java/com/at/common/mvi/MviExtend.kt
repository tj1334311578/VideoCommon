package com.at.common.mvi

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.reflect.KProperty1

/**
 * @Create_time: 2022/4/12 14:09
 * @Description: mvi扩展方法
 */

internal data class StateTuple1<A>(val a: A)

internal data class StateTuple2<A, B>(val a: A, val b: B)

internal data class StateTuple3<A, B, C>(val a: A, val b: B, val c: C)

/**
 * 局部状态 将状态转换并且过滤连续重复的事件
 * distinct 过滤重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3
 * distinctUntilChanged 过滤连续重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3 1 2 3
 * @param property 类似于java的Filed，直接可以反射获取到属性
 * @param action
 */
fun<T, A> LiveData<T>.observeState(lifecycleOwner: LifecycleOwner, property: KProperty1<T, A>,
                                   action: (A) -> Unit) {
    this.map {
        StateTuple1(property.get(it))
    }.distinctUntilChanged().observe(lifecycleOwner) { (a) ->
        action(a)
    }
}

/**
 * 局部状态 将状态转换并且过滤连续重复的事件
 * distinct 过滤重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3
 * distinctUntilChanged 过滤连续重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3 1 2 3
 * @param property 类似于java的Filed，直接可以反射获取到属性
 * @param action
 */
suspend fun<T, A> Flow<T>.collectState(property: KProperty1<T, A>, action: (A) -> Unit) {
    this.map {
        StateTuple1(property.get(it))
    }.distinctUntilChanged()
        .collectLatest { (a) ->
            action(a)
        }
}

/**
 * 局部状态 将状态转换并且过滤连续重复的事件
 * distinct 过滤重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3
 * distinctUntilChanged 过滤连续重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3 1 2 3
 * @param property1
 * @param property2
 * @param action
 */
fun<T, A, B> LiveData<T>.observeState(lifecycleOwner: LifecycleOwner, property1: KProperty1<T, A>,
                                      property2: KProperty1<T, B>, action: (A, B) -> Unit) {
    this.map {
        StateTuple2(property1.get(it), property2.get(it))
    }.distinctUntilChanged().observe(lifecycleOwner) { (a, b) ->
        action(a, b)
    }
}

/**
 * 局部状态 将状态转换并且过滤连续重复的事件
 * distinct 过滤重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3
 * distinctUntilChanged 过滤连续重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3 1 2 3
 * @param property1
 * @param property2
 * @param action
 */
suspend fun<T, A, B> Flow<T>.collectState(property1: KProperty1<T, A>, property2: KProperty1<T, B>,
                                          action: (A, B) -> Unit) {
    this.map {
        StateTuple2(property1.get(it), property2.get(it))
    }.distinctUntilChanged()
        .collectLatest { (a, b) ->
            action(a, b)
        }
}

/**
 * 局部状态 将状态转换并且过滤连续重复的事件
 * distinct 过滤重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3
 * distinctUntilChanged 过滤连续重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3 1 2 3
 * @param property1
 * @param property2
 * @param property3
 * @param action
 */
fun<T, A, B, C> LiveData<T>.observeState(lifecycleOwner: LifecycleOwner,
                                         property1: KProperty1<T, A>,
                                         property2: KProperty1<T, B>, property3: KProperty1<T, C>,
                                         action: (A, B, C) -> Unit) {
    this.map {
        StateTuple3(property1.get(it), property2.get(it), property3.get(it))
    }.distinctUntilChanged().observe(lifecycleOwner) { (a, b, c) ->
        action(a, b, c)
    }
}

/**
 * 局部状态 将状态转换并且过滤连续重复的事件
 * distinct 过滤重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3
 * distinctUntilChanged 过滤连续重复的事件 比如： 1 1 2 2 3 3 1 2 3 接收事件结果是 1 2 3 1 2 3
 * @param property1
 * @param property2
 * @param property3
 * @param action
 */
suspend fun<T, A, B, C> Flow<T>.collectState(property1: KProperty1<T, A>,
                                             property2: KProperty1<T, B>,
                                             property3: KProperty1<T, C>,
                                             action: (A, B, C) -> Unit) {
    this.map {
        StateTuple3(property1.get(it), property2.get(it), property3.get(it))
    }.distinctUntilChanged()
        .collectLatest { (a, b, c) ->
            action(a, b, c)
        }
}