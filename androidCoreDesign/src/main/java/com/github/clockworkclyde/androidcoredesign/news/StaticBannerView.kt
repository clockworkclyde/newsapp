package com.github.clockworkclyde.androidcoredesign.news

import android.content.Context
import android.util.AttributeSet
import com.github.clockworkclyde.androidcore.utils.safeClick
import com.github.clockworkclyde.androidcoredesign.R
import com.github.clockworkclyde.androidcoredesign.news.base.BannerView
import com.github.clockworkclyde.newsapp.domain.model.news.Banner

class StaticBannerView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) :
   BannerView(context, attrs, defStyleAttr) {

   override val layoutResId: Int = R.layout.layout_static_banner

   override val imageViewResId: Int = R.id.bannerIV

   var onItemClick: (Banner) -> Unit = { }

   private val bannerCornerRadius =
      context.resources.getDimensionPixelOffset(R.dimen.radius_image_article)

   fun loadStaticBannerView(item: Banner) {
      image?.let {
         loadRoundedImage(
            url = item.imageUrl,
            tag = item.id,
            cornerRadius = bannerCornerRadius
         )
         it.safeClick { onItemClick.invoke(item)  }
      }
   }

}