package com.github.clockworkclyde.newsapp.android.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.github.clockworkclyde.androidcore.presentation.fragments.BaseDataBindingFragment
import com.github.clockworkclyde.androidcore.utils.toast
import com.github.clockworkclyde.androidcore.utils.unsafeLazy
import com.github.clockworkclyde.newsapp.android.R
import com.github.clockworkclyde.newsapp.android.databinding.FragmentNewsBinding
import com.github.clockworkclyde.newsapp.android.presentation.news.items.AnimatedBannerItem
import com.github.clockworkclyde.newsapp.android.presentation.news.items.ArticleItem
import com.github.clockworkclyde.newsapp.android.presentation.news.items.NewsItem
import com.github.clockworkclyde.newsapp.common.utils.applyIfEmpty
import com.github.clockworkclyde.newsapp.common.utils.applyIfError
import com.github.clockworkclyde.newsapp.domain.model.news.Article
import com.github.clockworkclyde.newsapp.domain.model.news.Banner
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class NewsFragment : BaseDataBindingFragment<FragmentNewsBinding, NewsViewModel>() {

   override val layoutResId: Int = R.layout.fragment_news

   override val viewModel: NewsViewModel by viewModels()

   private val adapter by unsafeLazy { ItemAdapter<NewsItem<*>>() }
   private val fastAdapter by unsafeLazy { FastAdapter.with(adapter) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      lifecycle.addObserver(viewModel)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      lifecycle.removeObserver(viewModel)
   }

   override fun initViews() {
      setUpNewsList()
      observeErrorMessages()
      observeNewsItems()
   }

   override fun initBinding(binding: FragmentNewsBinding) {
      binding.vm = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
   }

   private fun setUpNewsList() {
      fastAdapter
      fastAdapter.onClickListener = { _: View?, _, item: NewsItem<*>, pos ->
         when (item) {
            is ArticleItem -> {
               item.onItemClick = { viewModel.onArticleClicked(pos, it) }
               true
            }
            is AnimatedBannerItem -> {
               item.onItemClick = {
                  viewModel.onBannerClicked(pos, it)
                  // todo: open link -> it.bannerUrl
               }
               true
            }
            else -> false
         }
      }

      binding.newsRV.itemAnimator = DefaultItemAnimator()
      binding.newsRV.adapter = fastAdapter
   }

   private fun observeErrorMessages() {
      viewModel.resultFlow.collectWhileStarted {
         it.applyIfError { error ->
            toast(error)
         }.applyIfEmpty {
            toast(getString(R.string.toast_empty_news_list))
         }
      }
   }

   private fun observeNewsItems() {
      viewModel.items.collectWhileStarted { items ->
         adapter.clear()
         adapter.add(items.map { it.convertTo() })
      }
   }

   // Convert to adapter items
   private fun NewsContentItem.convertTo(): NewsItem<*> {
      return when (this) {
         is Article -> ArticleItem().withItem(this)
         is Banner -> AnimatedBannerItem().withItem(this)
      }
   }
}