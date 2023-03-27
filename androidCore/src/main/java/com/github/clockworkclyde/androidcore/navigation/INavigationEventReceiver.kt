package com.github.clockworkclyde.androidcore.navigation

import com.github.clockworkclyde.androidcore.navigation.directions.INavEvent

interface INavigationEventReceiver {
   fun processNavEvent(event: INavEvent)
}