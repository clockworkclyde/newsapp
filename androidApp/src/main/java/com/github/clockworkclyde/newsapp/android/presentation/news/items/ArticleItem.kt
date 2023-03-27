package com.github.clockworkclyde.newsapp.android.presentation.news.items

import android.view.View
import com.github.clockworkclyde.androidcoredesign.news.ArticleView
import com.github.clockworkclyde.newsapp.android.R
import com.github.clockworkclyde.newsapp.domain.model.news.Article

class ArticleItem : NewsItem<Article>() {

   override var item: Article? = null

   override var onItemClick: (Article) -> Unit = {}

   override val type: Int = R.id.news_article_view_item_id

   override val layoutRes: Int = R.layout.item_article_view

   override fun withItem(item: Article): ArticleItem {
      this.item = item
      return this
   }

   override fun getViewHolder(v: View): NewsViewHolder<Article> {
      return ArticleViewHolder(v)
   }

   class ArticleViewHolder(view: View) : NewsViewHolder<Article>(view) {
      var articleView: ArticleView? = view.findViewById(R.id.articleView)

      override fun bindView(item: NewsItem<Article>, payloads: List<Any>) {
         articleView?.setUpView(item.item)
         articleView?.onItemClick = item.onItemClick
      }

      override fun unbindView(item: NewsItem<Article>) {
         articleView?.clearView()
      }
   }
}