package com.github.clockworkclyde.androidcore.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.github.clockworkclyde.androidcore.presentation.binding.IBaseDataBinding
import com.github.clockworkclyde.androidcore.presentation.viewmodels.BaseFlowViewModel
import com.github.clockworkclyde.androidcore.utils.inflateBindingLayout
import com.github.clockworkclyde.androidcore.utils.launchAndRepeatOnState
import com.github.clockworkclyde.androidcore.utils.toast
import com.github.clockworkclyde.newsapp.common.dto.DResult
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

abstract class BaseDataBindingFragment<V : ViewDataBinding, VM : BaseFlowViewModel>
   : Fragment(), IBaseFragment<VM>, IBaseDataBinding<V> {

   override val viewModel: VM? = null

   private var _binding: V? = null
   override val binding: V get() = _binding ?: throw IllegalStateException("Cannot access view")

   override val navController: NavController
      get() = findNavController()

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      setUpBindingView(inflater, container)
      initBinding(binding)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      setUpOnBackPressHandler()
      initViews()
      handleResultError()
   }

   override fun onStart() {
      super.onStart()
      handleNavigationEvent()
   }

   override fun setUpBindingView(inflater: LayoutInflater, container: ViewGroup?): View {
      return inflater.inflateBindingLayout<V>(layoutResId, container, false).let {
         _binding = it
         it.root
      }
   }

   override fun initViews() = Unit

   override fun initBinding(binding: V) = Unit

   override fun handleResultError() = Unit

   protected open fun setUpOnBackPressHandler() {
      requireActivity().onBackPressedDispatcher.addCallback(
         viewLifecycleOwner,
         onBackPressedCallback
      )
   }

   override val onBackPressAllowed: Boolean
      get() = true

   protected open val onBackPressedCallback by lazy {
      object : OnBackPressedCallback(onBackPressAllowed) {
         override fun handleOnBackPressed() {
            findNavController().popBackStack()
         }
      }
   }

   override fun handleNavigationEvent() {
      viewModel?.navigationFlow?.collectWhileStarted {
         navController.let(it)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      clearBinding()
   }

   override fun clearBinding() {
      _binding?.unbind()
      _binding = null
   }

   protected fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
      launchAndRepeatOnState(Lifecycle.State.STARTED) {
         collect { block(it) }
      }
   }

   protected fun Fragment.launchWhenStarted(block: suspend () -> Unit) {
      launchAndRepeatOnState(Lifecycle.State.STARTED) {
         block.invoke()
      }
   }

   protected fun Fragment.toast(error: DResult.ResultThrowable) = toast(
      "${error.message}, code : ${error.code}"
   ).also {
      Timber.e(error.exception)
   }
}