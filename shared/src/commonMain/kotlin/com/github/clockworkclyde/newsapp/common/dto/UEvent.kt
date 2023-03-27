package com.github.clockworkclyde.newsapp.common.dto

abstract class UEvent : IEvent {
   object Fetch : UEvent()
   object Retry : UEvent()
   object Refresh : UEvent()
   class Error(val error: DResult.ResultThrowable) : UEvent()
   object Validate : UEvent()
}