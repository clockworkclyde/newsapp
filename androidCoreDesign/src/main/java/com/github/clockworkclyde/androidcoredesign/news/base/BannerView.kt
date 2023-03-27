package com.github.clockworkclyde.androidcoredesign.news.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.clockworkclyde.androidcore.utils.loadRoundedImage

abstract class BannerView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

   abstract val layoutResId: Int
   abstract val imageViewResId: Int

   var layoutView: View? = null

   override fun onAttachedToWindow() {
      super.onAttachedToWindow()
      if (!isInEditMode) {
         setUpView()
      }
   }

   override fun onDetachedFromWindow() {
      super.onDetachedFromWindow()
      if (!isInEditMode) {
         layoutView = null
      }
   }

   protected open val isAttachedToParent: Boolean = true

   protected open fun setUpView() {
      layoutView = LayoutInflater.from(context)
         .inflate(layoutResId, this@BannerView, isAttachedToParent)
   }

   open val image: ImageView?
      get() = findViewById<ImageView>(imageViewResId)

   open var tag: Long = 0L

   protected open fun loadRoundedImage(url: String, tag: Long, cornerRadius: Int = 0) {
      rootView?.let { root ->
         image?.let { iv ->
            Glide.with(root).loadRoundedImage(url, iv, cornerRadius)
            this.tag = tag
         }
      }
   }
}