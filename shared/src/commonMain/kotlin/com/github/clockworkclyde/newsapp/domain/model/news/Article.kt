package com.github.clockworkclyde.newsapp.domain.model.news

data class Article(
   override val id: Long,
   val name: String,
   val image: ArticleImage,
   val description: String,
   val createdAt: String
) : NewsContentItem

data class ArticleImage(
   val id: Long,
   val url: String,
   val preview: String,
   val placeholderColor: String,
   val width: Int,
   val height: Int
)