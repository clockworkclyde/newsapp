package com.github.clockworkclyde.androidcore.presentation.viewmodels

import com.github.clockworkclyde.androidcore.dto.IEvent
import kotlinx.coroutines.flow.Flow

interface IBaseFlowViewModel {

   val eventsFlow: Flow<IEvent>
   val resultFlow: Flow<*>?
   val supportFlow: Flow<*>?

}