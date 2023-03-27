package com.github.clockworkclyde.androidcore.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun RequestManager.loadRoundedImage(
   imageUrl: String,
   view: ImageView,
   radius: Int,
   placeholderResId: Int? = null
) {
   this.load(imageUrl)
      .transform(
         CenterCrop(),
         RoundedCorners(
            radius
         )
      )
      .run {
         if (placeholderResId != null) this.placeholder(
            AppCompatResources.getDrawable(
               view.context,
               placeholderResId
            )
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