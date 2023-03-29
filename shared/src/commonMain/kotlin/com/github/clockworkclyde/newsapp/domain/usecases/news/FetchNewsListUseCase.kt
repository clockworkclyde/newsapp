package com.github.clockworkclyde.newsapp.domain.usecases.news

import com.github.clockworkclyde.newsapp.common.ResultList
import com.github.clockworkclyde.newsapp.common.utils.mapToSuccessResultOrEmpty
import com.github.clockworkclyde.newsapp.common.utils.runResultSuspendCatch
import com.github.clockworkclyde.newsapp.di.kodein
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import com.github.clockworkclyde.newsapp.domain.usecases.IUseCase
import com.github.clockworkclyde.newsapp.providers.NewsApi
import io.ktor.client.engine.*
import org.kodein.di.instance

class FetchNewsListUseCase(
   httpClientEngine: HttpClientEngine
) : IFetchNewsListUseCase {

   private val api: NewsApi by kodein.instance(arg = httpClientEngine)

   override suspend fun invoke(): ResultList<NewsContentItem> {
      return runResultSuspendCatch {
         api.fetchNewsList().responseData.result.map { it.convertTo() }
            .mapToSuccessResultOrEmpty()
      }
   }
}

interface IFetchNewsListUseCase : IUseCase.Out<ResultList<NewsContentItem>>