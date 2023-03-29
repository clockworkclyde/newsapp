package com.github.clockworkclyde.newsapp.domain

import com.github.clockworkclyde.newsapp.PlatformServiceLocator
import com.github.clockworkclyde.newsapp.domain.usecases.news.FetchNewsListUseCase
import com.github.clockworkclyde.newsapp.domain.usecases.news.IFetchNewsListUseCase
import com.github.clockworkclyde.newsapp.domain.usecases.news.ILoadMoreUseCase
import com.github.clockworkclyde.newsapp.domain.usecases.news.LoadMoreUseCase
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceLocator {

   val fetchNewsListUseCase: IFetchNewsListUseCase
      get() = FetchNewsListUseCase(PlatformServiceLocator.httpClientEngine)

   val loadMoreNewsUseCase: ILoadMoreUseCase
      get() = LoadMoreUseCase(PlatformServiceLocator.httpClientEngine)

}
