package com.github.clockworkclyde.androidcoredesign.news

import android.content.Context
import android.util.AttributeSet
import com.github.clockworkclyde.androidcoredesign.R
import com.github.clockworkclyde.androidcoredesign.news.base.BannerView

class StaticBannerView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
   BannerView(context, attrs, defStyleAttr) {

   override val layoutResId: Int = R.layout.layout_static_banner

   override val imageViewResId: Int = R.id.bannerIV

}