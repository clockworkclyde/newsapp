package com.github.clockworkclyde.newsapp.domain.usecases.news

import com.github.clockworkclyde.newsapp.common.ResultList
import com.github.clockworkclyde.newsapp.common.utils.runResultSuspendCatch
import com.github.clockworkclyde.newsapp.common.utils.toSuccessResult
import com.github.clockworkclyde.newsapp.di.kodein
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import com.github.clockworkclyde.newsapp.domain.usecases.IUseCase
import com.github.clockworkclyde.newsapp.providers.NewsApi
import io.ktor.client.engine.*
import org.kodein.di.instance

class LoadMoreUseCase(httpClientEngine: HttpClientEngine) : ILoadMoreUseCase {

   private val api: NewsApi by kodein.instance(arg = httpClientEngine)

   override suspend fun invoke(
      firstParam: Int,
      secondParam: List<NewsContentItem>
   ): ResultList<NewsContentItem> {
      val nextPage = firstParam.inc()
      val currentData = secondParam.toMutableList()
      return runResultSuspendCatch {
         api.fetchNewsList(nextPage).responseData.result
            .mapTo(currentData) { it.convertTo() }.toSuccessResult()
      }
   }
}

interface ILoadMoreUseCase :
   IUseCase.DoubleInOut<Int, List<NewsContentItem>, ResultList<NewsContentItem>>