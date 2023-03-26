package com.github.clockworkclyde.androidcore.presentation.fragments

import androidx.navigation.NavController
import com.github.clockworkclyde.androidcore.navigation.INavigationEventHandler
import com.github.clockworkclyde.androidcore.presentation.viewmodels.IEventViewModel

interface IBaseFragment<VM: IEventViewModel>: INavigationEventHandler {

   val navController: NavController

   val layoutResId: Int

   val viewModel: VM?

   fun initViews()

   fun handleResultError()

   val onBackPressAllowed: Boolean

}