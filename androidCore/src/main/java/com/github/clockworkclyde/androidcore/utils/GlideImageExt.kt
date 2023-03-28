package com.github.clockworkclyde.androidcore.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun RequestManager.loadRoundedImage(
   imageUrl: String,
   view: ImageView,
   radius: Int,
   placeholderColorResId: String? = null
) {
   this.load(imageUrl)
      .transform(
         CenterCrop(),
         RoundedCorners(
            radius
         )
      )
      .run {
         if (placeholderColorResId != null) this.placeholder(
            ColorDrawable(Color.parseColor(placeholderColorResId))
         )
         else this
      }
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(view)
}

fun RequestManager.loadCircleRoundedBitmap(bitmap: Bitmap, view: ImageView) {
   this.asBitmap()
      .load(bitmap)
      .circleCrop()
      .into(view)
}