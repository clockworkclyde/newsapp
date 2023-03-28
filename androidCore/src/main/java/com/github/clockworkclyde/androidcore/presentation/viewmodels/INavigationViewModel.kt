package com.github.clockworkclyde.androidcore.presentation.viewmodels

import com.github.clockworkclyde.androidcore.navigation.directions.INavDirections

interface INavigationViewModel<T: INavDirections> {
   val directions: T
}