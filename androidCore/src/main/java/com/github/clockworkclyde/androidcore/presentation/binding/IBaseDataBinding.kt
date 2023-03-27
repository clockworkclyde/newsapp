package com.github.clockworkclyde.androidcore.presentation.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

interface IBaseDataBinding<V: ViewDataBinding> {
   val binding: V
   fun setUpBindingView(inflater: LayoutInflater, container: ViewGroup?): View
   fun initBinding(binding: V)
   fun clearBinding()
}