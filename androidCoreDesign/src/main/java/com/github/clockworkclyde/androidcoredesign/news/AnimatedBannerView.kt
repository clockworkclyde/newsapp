package com.github.clockworkclyde.androidcoredesign.news

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ImageViewTarget
import com.github.clockworkclyde.androidcore.utils.safeClick
import com.github.clockworkclyde.androidcoredesign.R
import com.github.clockworkclyde.androidcoredesign.databinding.LayoutAnimatedBannerBinding
import com.github.clockworkclyde.androidcoredesign.news.base.BannerView
import com.github.clockworkclyde.newsapp.domain.model.news.Banner

class AnimatedBannerView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) : BannerView<LayoutAnimatedBannerBinding, Banner>(context, attrs, defStyleAttr) {

   override val layoutResId: Int get() = R.layout.layout_animated_banner

   var onItemClick: (Banner) -> Unit = { }

   private val bannerCornerRadius =
      context.resources.getDimensionPixelOffset(R.dimen.radius_image_article)

   override fun loadBannerView(item: Banner) {
      with(binding) {
         Glide.with(root)
            .asGif()
            .load(item.imageUrl)
            .into(object : ImageViewTarget<GifDrawable>(bannerIV) {
               override fun setResource(resource: GifDrawable?) {
                  bannerIV.setImageDrawable(resource)
               }
            })
         tag = item.id
         binding.root.safeClick { onItemClick.invoke(item) }
      }
   }
}