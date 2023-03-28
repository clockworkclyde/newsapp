package com.github.clockworkclyde.newsapp.android.navigation.news

import com.github.clockworkclyde.androidcore.navigation.directions.INavDirections
import com.github.clockworkclyde.androidcore.navigation.directions.INavEvent
import com.github.clockworkclyde.newsapp.android.presentation.details.ArticleDetails
import com.github.clockworkclyde.newsapp.android.presentation.news.NewsFragmentDirections

class NewsDirections
   : INavDirections {

   fun toDetails(article: ArticleDetails) =
      INavEvent.ShowScreen(NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article))

}