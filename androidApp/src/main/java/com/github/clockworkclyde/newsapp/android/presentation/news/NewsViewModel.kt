package com.github.clockworkclyde.newsapp.android.presentation.news

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.androidcore.presentation.viewmodels.BaseFlowViewModel
import com.github.clockworkclyde.androidcore.utils.onEventFlow
import com.github.clockworkclyde.androidcore.utils.unsafeLazy
import com.github.clockworkclyde.newsapp.common.AnyResult
import com.github.clockworkclyde.newsapp.common.FlowResult
import com.github.clockworkclyde.newsapp.common.ResultList
import com.github.clockworkclyde.newsapp.common.dto.UEvent
import com.github.clockworkclyde.newsapp.common.utils.applyIfError
import com.github.clockworkclyde.newsapp.common.utils.applyIfSuccess
import com.github.clockworkclyde.newsapp.common.utils.emptyResult
import com.github.clockworkclyde.newsapp.common.utils.loadingResult
import com.github.clockworkclyde.newsapp.domain.ServiceLocator
import com.github.clockworkclyde.newsapp.domain.model.news.Article
import com.github.clockworkclyde.newsapp.domain.model.news.Banner
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel : BaseFlowViewModel(), DefaultLifecycleObserver {

   /**
    * Текущий список новостей и баннеров
    */
   private val _items = MutableStateFlow<List<NewsContentItem>>(emptyList())
   val items = _items.asStateFlow()

   private val currentPage = MutableStateFlow(INITIAL_PAGE)

   private suspend fun fetchItems() = ServiceLocator.fetchNewsListUseCase()

   private suspend fun tryLoadMoreItems() =
      ServiceLocator.loadMoreNewsUseCase(currentPage.value, items.value)

   /**
    * Состояние экрана
    **/
   override val resultFlow: FlowResult<*> by unsafeLazy {
      onEventFlow<UEvent.Fetch, ResultList<NewsContentItem>> {
         emit(loadingResult())
         emit(fetchItems().applyIfSuccess { _items.value = it })
      }
   }

   val currentStateFlow: StateFlow<AnyResult> by unsafeLazy {
      resultFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyResult())
   }

   fun onArticleClicked(index: Int, article: Article) {
      // go to article details
      Timber.d("Article was clicked by $index")
   }

   fun onBannerClicked(index: Int, banner: Banner) {
      Timber.d("User goes to $banner by click from $index")
   }

   fun onTryToLoadMore(index: Int) {
      if (index != items.value.size + 1) return
      viewModelScope.launch {
         tryLoadMoreItems()
            .applyIfSuccess {
               _items.value = it
               currentPage.value = currentPage.value.inc()
            }
            .applyIfError { /*TODO: handle error*/ }
      }
   }

   override fun onStart(owner: LifecycleOwner) {
      super.onStart(owner)
      if (items.value.isEmpty()) processEvent(UEvent.Fetch)
   }

   fun onRetryFetch() {
      processEvent(UEvent.Fetch)
   }

   companion object {
      const val INITIAL_PAGE = 0
   }
}