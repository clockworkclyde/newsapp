package com.github.clockworkclyde.newsapp.domain.model.news

data class Banner(
   override val id: Long,
   val bannerUrl: String,
   val imageUrl: String,
   val width: Int,
   val height: Int
) : NewsContentItem