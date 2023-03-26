package com.github.clockworkclyde.androidcore.presentation.viewmodels

import kotlinx.coroutines.channels.BufferOverflow

data class SharedFlowOptions(
   val replay: Int,
   val bufferCapacity: Int,
   val bufferOverflow: BufferOverflow
) {

   companion object {
      @Suppress("unused", "FunctionName")
      fun NoReplay() = SharedFlowOptions(
         replay = 0,
         bufferCapacity = 1,
         bufferOverflow = BufferOverflow.DROP_OLDEST
      )
      @Suppress("unused", "FunctionName")
      fun Replay() = SharedFlowOptions(
         replay = 1,
         bufferCapacity = 1,
         bufferOverflow = BufferOverflow.DROP_OLDEST
      )
   }
}