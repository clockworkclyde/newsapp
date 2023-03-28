package com.github.clockworkclyde.androidcoredesign.news

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.github.clockworkclyde.androidcore.utils.loadGif
import com.github.clockworkclyde.androidcore.utils.safeClick
import com.github.clockworkclyde.androidcoredesign.R
import com.github.clockworkclyde.androidcoredesign.news.base.BannerView
import com.github.clockworkclyde.newsapp.domain.model.news.Banner

class AnimatedBannerView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) : BannerView(context, attrs, defStyleAttr) {

   override val layoutResId: Int = R.layout.layout_animated_banner

   override val imageViewResId: Int = R.id.bannerIV

   var onItemClick: (Banner) -> Unit = { }

   private val bannerCornerRadius =
      context.resources.getDimensionPixelOffset(R.dimen.radius_image_article)

   fun loadAnimatedBannerView(item: Banner) {
      layoutView?.let { root ->
         imageView?.let {
            Glide.with(root).loadGif(
               gifImageUrl = item.imageUrl,
               view = it,
               radius = bannerCornerRadius,
               placeholderResId = R.drawable.bg_banner_placeholder
            )
            tag = item.id
            it.safeClick { onItemClick.invoke(item) }
         }
      }
   }

   override fun clearView() {
      onItemClick = {}
      image = null
   }
}