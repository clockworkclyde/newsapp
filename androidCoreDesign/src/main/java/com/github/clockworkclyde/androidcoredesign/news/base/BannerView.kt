package com.github.clockworkclyde.androidcoredesign.news.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.ViewDataBinding
import com.github.clockworkclyde.androidcore.presentation.binding.IBaseDataBinding
import com.github.clockworkclyde.androidcore.utils.inflateBindingLayout

abstract class BannerView<V : ViewDataBinding, in T : Any> @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IBannerView, IBaseDataBinding<V> {

   private var _binding: V? = null
   override val binding: V get() = _binding ?: error("Cannot access view")

   open val isAttachedToParent: Boolean = true

   private fun setUpView(context: Context) {
      if (!isInEditMode) {
         _binding = LayoutInflater.from(context)
            .inflateBindingLayout(layoutResId, this@BannerView, isAttachedToParent)
         initBinding(binding)
      } else inflate(context, layoutResId, this@BannerView)
   }

   override fun setUpBindingView(inflater: LayoutInflater, container: ViewGroup?): View {
      return LayoutInflater.from(context)
         .inflateBindingLayout<V>(layoutResId, container, isAttachedToParent).let {
            _binding = it
            it.root
         }
   }

   override fun clearView() = Unit

   open var tag: Long = 0L

   abstract fun loadBannerView(item: T)

   override fun initBinding(binding: V) = Unit

   override fun clearBinding() {
      _binding?.unbind()
      _binding = null
   }

   init {
      setUpView(context)
   }
}