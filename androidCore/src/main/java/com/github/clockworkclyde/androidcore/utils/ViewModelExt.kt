package com.github.clockworkclyde.androidcore.utils

import com.github.clockworkclyde.androidcore.dto.IEvent
import com.github.clockworkclyde.androidcore.presentation.viewmodels.IBaseFlowViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <reified E : IEvent, reified T : Any> IBaseFlowViewModel.onEventFlow(
   dispatcher: CoroutineDispatcher = Dispatchers.IO,
   isDistinctUntilChanged: Boolean = false,
   noinline catch: ((Throwable) -> Unit)? = null,
   crossinline block: suspend FlowCollector<T>.(E) -> Unit,
): Flow<T> {
   val events = if (isDistinctUntilChanged) eventsFlow.distinctUntilChanged() else eventsFlow
   return flow {
      events.collect {
         if (it is E) {
            this@flow.block(it)
         }
      }
   }.flowOn(dispatcher)
//      .catch {
//      catch?.invoke(it)
//   }
}