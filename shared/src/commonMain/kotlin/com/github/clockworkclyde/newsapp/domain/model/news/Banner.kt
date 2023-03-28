package com.github.clockworkclyde.newsapp.domain.model.news

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Banner(
   @SerialName("id") override val id: Long,
   @SerialName("url") val bannerUrl: String,
   @SerialName("image") val imageUrl: String,
   @SerialName("width") val width: Int,
   @SerialName("height") val height: Int
) : NewsContentItem