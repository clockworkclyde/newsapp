package com.github.clockworkclyde.androidcore.navigation.directions

import androidx.navigation.NavDirections
import com.github.clockworkclyde.newsapp.common.dto.IEvent

sealed interface INavEvent: IEvent {

   class ShowScreen(val directions: NavDirections, val popUpTo: Int? = null, val inclusive: Boolean = false):
      INavEvent

   class BackTo(val layoutId: Int, val inclusive: Boolean = false): INavEvent

   object PopBackTo : INavEvent

}

