package com.github.clockworkclyde.newsapp.android.presentation.news

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.github.clockworkclyde.androidcore.presentation.fragments.BaseDataBindingFragment
import com.github.clockworkclyde.androidcore.utils.postDelayed
import com.github.clockworkclyde.androidcore.utils.toast
import com.github.clockworkclyde.androidcore.utils.unsafeLazy
import com.github.clockworkclyde.newsapp.android.R
import com.github.clockworkclyde.newsapp.android.databinding.FragmentNewsBinding
import com.github.clockworkclyde.newsapp.android.presentation.news.items.AnimatedBannerItem
import com.github.clockworkclyde.newsapp.android.presentation.news.items.ArticleItem
import com.github.clockworkclyde.newsapp.android.presentation.news.items.NewsItem
import com.github.clockworkclyde.newsapp.common.utils.applyIfError
import com.github.clockworkclyde.newsapp.domain.model.news.Article
import com.github.clockworkclyde.newsapp.domain.model.news.Banner
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.OnBindViewHolderListenerImpl

class NewsFragment : BaseDataBindingFragment<FragmentNewsBinding, NewsViewModel>() {

   override val layoutResId: Int = R.layout.fragment_news

   override val viewModel: NewsViewModel by viewModels()

   private val adapter by unsafeLazy { ItemAdapter<NewsItem<*>>() }
   private val fastAdapter by unsafeLazy { GenericFastAdapter.with(adapter) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      lifecycle.addObserver(viewModel)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      lifecycle.removeObserver(viewModel)
   }

   override fun initViews() {
      setUpToolbar()
      setUpNewsList()
      observeErrorMessages()
      observeNewsItems()
   }

   override fun initBinding(binding: FragmentNewsBinding) {
      binding.vm = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
   }

   private fun setUpToolbar() {
      binding.toolbar.apply {
         setNavigationOnClickListener { toast("Arrow back clicked!") }
      }
   }

   private fun setUpNewsList() {

      // Pass bind position to viewModel to detect last position in @items for pagination
      fastAdapter.onBindViewHolderListener = object : OnBindViewHolderListenerImpl<NewsItem<*>>() {
         override fun onBindViewHolder(
            viewHolder: RecyclerView.ViewHolder,
            position: Int,
            payloads: List<Any>
         ) {
            super.onBindViewHolder(viewHolder, position, payloads)
            viewModel.onTryToLoadMore(position)
            viewModel.onBannerDisplayed(position)
         }
      }

      binding.newsRV.itemAnimator = DefaultItemAnimator()
      binding.newsRV.adapter = fastAdapter
   }

   private fun observeErrorMessages() {
      viewModel.currentStateFlow.collectWhileStarted {
         it.applyIfError { error ->
            toast(error)
         }
      }
   }

   private fun observeNewsItems() {
      viewModel.items.collectWhileStarted { items ->
         adapter.set(items.mapIndexed { i, item -> item.convertTo(i) })
      }
   }

   // Convert to adapter items
   private fun NewsContentItem.convertTo(index: Int): NewsItem<*> {
      return when (this) {
         is Article -> ArticleItem().withItem(this)
            .also {
               it.onItemClick =
                  { article -> postDelayed { viewModel.onArticleClicked(index, article) } }
            }
         is Banner -> AnimatedBannerItem().withItem(this)
            .also {
               it.onItemClick = { banner ->
                  postDelayed {
                     viewModel.onBannerClicked(index, banner)
                     // todo: go to banner url
                  }
               }
            }
      }
   }

   override fun onSaveInstanceState(_outState: Bundle) {
      var outState = _outState
      outState = fastAdapter.saveInstanceState(outState)
      super.onSaveInstanceState(outState)
   }
}