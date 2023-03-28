package com.github.clockworkclyde.newsapp

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import okhttp3.logging.HttpLoggingInterceptor

actual object PlatformServiceLocator {

   actual val httpClientEngine: HttpClientEngine by lazy {
      OkHttp.create {
         val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
         }

         addNetworkInterceptor(interceptor)
      }
   }

}