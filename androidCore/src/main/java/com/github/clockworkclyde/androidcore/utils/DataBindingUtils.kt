package com.github.clockworkclyde.androidcore.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

@BindingAdapter("android:safeClick")
fun safeClickUnitHandler(view: View, onSafeClick: com.github.clockworkclyde.newsapp.common.UnitHandler) {
   view.safeClick { onSafeClick() }
}

@BindingAdapter("android:onValueChange")
fun onValueChangeOrEmpty(view: EditText, onValueChange: (String) -> Unit) {
   view.onTextChanged { onValueChange(it) }
}

fun <V : ViewDataBinding> LayoutInflater.inflateBindingLayout(
   @LayoutRes layoutResId: Int,
   container: ViewGroup?,
   isAttached: Boolean
): V = DataBindingUtil.inflate<V>(this, layoutResId, container, isAttached)

