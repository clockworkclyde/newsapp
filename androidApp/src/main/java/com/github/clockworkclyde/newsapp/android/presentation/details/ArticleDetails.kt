package com.github.clockworkclyde.newsapp.android.presentation.details

import com.github.clockworkclyde.newsapp.Parcelable
import com.github.clockworkclyde.newsapp.Parcelize
import com.github.clockworkclyde.newsapp.domain.model.news.Article

@Parcelize
data class ArticleDetails(
   val id: Long,
   val name: String,
   val image: ArticleImageUi,
   val description: String,
   val createdAt: Long
) : Parcelable

@Parcelize
data class ArticleImageUi(
   val id: String,
   val url: String,
   val preview: String,
   val placeholderColor: String,
   val width: Int,
   val height: Int
) : Parcelable

fun Article.convertTo() = ArticleDetails(
   id = id,
   name = name,
   image = ArticleImageUi(
      id = image.id,
      url = image.url,
      preview = image.preview,
      placeholderColor = image.placeholderColor,
      width = image.width,
      height = image.height
   ),
   description = description,
   createdAt = createdAt
)