package com.github.clockworkclyde.newsapp.domain.model.news

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Article(
   @SerialName("id") override val id: Long,
   @SerialName("name") val name: String,
   @SerialName("image") val image: ArticleImage,
   @SerialName("description") val description: String,
   @SerialName("create_date") val createdAt: Long
) : NewsContentItem

@kotlinx.serialization.Serializable
data class ArticleImage(
   @SerialName("id") val id: String,
   @SerialName("url") val url: String,
   @SerialName("preview") val preview: String,
   @SerialName("placeholder_color") val placeholderColor: String,
   @SerialName("width") val width: Int,
   @SerialName("height") val height: Int
)