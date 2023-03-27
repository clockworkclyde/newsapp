package com.github.clockworkclyde.newsapp.common

import com.github.clockworkclyde.newsapp.common.dto.DResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias UnitHandler = () -> Unit
typealias InHandler<T> = (T) -> Unit
typealias DoubleInHandler<T, R> = (T, R) -> Unit
typealias OutHandler<T> = () -> T
typealias InOutHandler<T> = (T) -> T

typealias FlowList<T> = Flow<List<T>>
typealias FlowResultList<T> = Flow<DResult<List<T>>>
typealias FlowResult<T> = Flow<DResult<T>>
typealias FlowAnyResult = Flow<DResult<Any>>

typealias AnyResult = DResult<Any>
typealias ResultList<T> = DResult<List<T>>
typealias ErrorThrowableResult = DResult.ResultThrowable
typealias ErrorResult = DResult.ResultThrowable.Error
typealias SuccessResult<T> = DResult.Success<T>

typealias MutableStateFlowList<T> = MutableStateFlow<List<T>>
typealias StateFlowList<T> = StateFlow<List<T>>
typealias StateFlowResult<T> = StateFlow<DResult<T>>
typealias MutableStateFlowResult<T> = MutableStateFlow<DResult<T>>