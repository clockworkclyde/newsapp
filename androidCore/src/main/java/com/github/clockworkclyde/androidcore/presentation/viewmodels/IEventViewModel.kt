package com.github.clockworkclyde.androidcore.presentation.viewmodels

import com.github.clockworkclyde.newsapp.common.dto.IEvent

interface IEventViewModel {
   fun processEvent(event: IEvent)
}