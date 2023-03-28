package com.github.clockworkclyde.androidcore.presentation.viewmodels

import com.github.clockworkclyde.androidcore.navigation.NavigationHandler
import com.github.clockworkclyde.androidcore.utils.unsafeLazy
import com.github.clockworkclyde.newsapp.common.dto.IEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

open class BaseFlowViewModel : NavigationViewModel(), IBaseFlowViewModel, IEventViewModel {

   open val sharedFlowOptions = SharedFlowOptions.Replay()
   open val navigationFlowOptions = SharedFlowOptions.NoReplay()

   override val navigationFlow: MutableSharedFlow<NavigationHandler> by unsafeLazy {
      MutableSharedFlow(
         replay = navigationFlowOptions.replay,
         extraBufferCapacity = navigationFlowOptions.bufferCapacity,
         onBufferOverflow = navigationFlowOptions.bufferOverflow
      )
   }

   override val eventsFlow: MutableSharedFlow<IEvent> by unsafeLazy {
      MutableSharedFlow(
         replay = sharedFlowOptions.replay,
         extraBufferCapacity = sharedFlowOptions.bufferCapacity,
         onBufferOverflow = sharedFlowOptions.bufferOverflow
      )
   }

   override val resultFlow: Flow<*>? = null
   override val supportFlow: Flow<*>? = null

   override fun processEvent(event: IEvent) {
      eventsFlow.tryEmit(event)
   }
}