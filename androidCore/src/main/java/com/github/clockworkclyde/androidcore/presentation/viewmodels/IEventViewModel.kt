package com.github.clockworkclyde.androidcore.presentation.viewmodels

import com.github.clockworkclyde.androidcore.dto.IEvent

interface IEventViewModel {
   fun processEvent(event: IEvent)
}