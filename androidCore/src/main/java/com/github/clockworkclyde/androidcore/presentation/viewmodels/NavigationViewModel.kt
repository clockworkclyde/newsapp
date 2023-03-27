package com.github.clockworkclyde.androidcore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.github.clockworkclyde.androidcore.common.NavigationHandler
import com.github.clockworkclyde.androidcore.navigation.INavigationEventReceiver
import com.github.clockworkclyde.androidcore.navigation.INavigator
import com.github.clockworkclyde.androidcore.navigation.directions.INavEvent
import com.github.clockworkclyde.androidcore.utils.safeNavigate
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class NavigationViewModel : ViewModel(), INavigator, INavigationEventReceiver {

   abstract val navigationFlow: MutableSharedFlow<NavigationHandler>

   override fun navigateTo(direction: NavDirections, navOptions: NavOptions?) {
      pushToNavigationCommands { it.safeNavigate(direction, navOptions) }
   }

   override fun popBackStack(): Boolean {
      var popUp = false
      pushToNavigationCommands { popUp = it.popBackStack() }
      return popUp
   }

   override fun backTo(destinationId: Int, inclusive: Boolean): Boolean {
      var popUp = false
      pushToNavigationCommands { popUp = it.popBackStack(destinationId, inclusive) }
      return popUp
   }

   protected open fun pushToNavigationCommands(handler: NavigationHandler) {
      navigationFlow.tryEmit(handler)
   }

   override fun processNavEvent(event: INavEvent) {
      when (event) {
         is INavEvent.BackTo -> {
            backTo(event.layoutId, event.inclusive)
         }
         is INavEvent.ShowScreen -> navigateTo(
            direction = getDirectionByActionId(event.id),
            navOptions = navOptions(event.popUpTo, event.inclusive)
         )
         is INavEvent.PopBackTo -> popBackStack()
      }
   }

   private fun getDirectionByActionId(actionId: Int): NavDirections =
      ActionOnlyNavDirections(actionId)

   private fun navOptions(popUpTo: Int?, inclusive: Boolean?) =
      if (popUpTo != null && inclusive != null) {
         NavOptions.Builder()
            .setPopUpTo(popUpTo, inclusive)
            .build()
      } else null
}