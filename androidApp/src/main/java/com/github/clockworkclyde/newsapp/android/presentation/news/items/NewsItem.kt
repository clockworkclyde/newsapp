package com.github.clockworkclyde.newsapp.android.presentation.news.items

import android.view.View
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

abstract class NewsItem<T: NewsContentItem>: AbstractItem<NewsItem.NewsViewHolder<T>>() {

   abstract var item: T?

   abstract var onItemClick: (T) -> Unit

   abstract fun withItem(item: T): NewsItem<T>

   abstract class NewsViewHolder<T: NewsContentItem>(v: View): FastAdapter.ViewHolder<NewsItem<T>>(v)
}