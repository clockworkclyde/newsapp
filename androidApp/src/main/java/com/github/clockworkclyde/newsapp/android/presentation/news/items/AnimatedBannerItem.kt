package com.github.clockworkclyde.newsapp.android.presentation.news.items

import android.view.View
import com.github.clockworkclyde.androidcoredesign.news.AnimatedBannerView
import com.github.clockworkclyde.newsapp.android.R
import com.github.clockworkclyde.newsapp.domain.model.news.Banner

class AnimatedBannerItem : NewsItem<Banner>() {

   override var item: Banner? = null

   override var onItemClick: (Banner) -> Unit = {}

   override val type: Int = R.id.news_animated_banner_item_id

   override val layoutRes: Int = R.layout.item_animated_banner

   override fun withItem(item: Banner): AnimatedBannerItem {
      this.item = item
      return this
   }

   override fun getViewHolder(v: View): NewsViewHolder<Banner> {
      return BannerViewHolder(v)
   }

   class BannerViewHolder(view: View) : NewsViewHolder<Banner>(view) {
      var bannerView: AnimatedBannerView? = view.findViewById(R.id.animatedBannerView)

      override fun bindView(item: NewsItem<Banner>, payloads: List<Any>) {
         item.item?.let { bannerView?.loadBannerView(it) }
         bannerView?.onItemClick = item.onItemClick
      }

      override fun unbindView(item: NewsItem<Banner>) {
         bannerView?.clearView()
      }
   }
}