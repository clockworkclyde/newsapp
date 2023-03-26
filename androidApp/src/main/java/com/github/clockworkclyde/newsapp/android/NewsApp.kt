package com.github.clockworkclyde.newsapp.android

import android.app.Application
import timber.log.Timber

class NewsApp: Application() {

   override fun onCreate() {
      super.onCreate()
      Timber.plant(Timber.DebugTree())
   }
}