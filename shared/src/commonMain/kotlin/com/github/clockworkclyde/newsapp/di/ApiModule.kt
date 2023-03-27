package com.github.clockworkclyde.newsapp.di

import com.github.clockworkclyde.newsapp.providers.NewsApi
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.with

val apiModule = DI.Module("api") {

   constant(tag = "newsBaseUrl") with "partnerkin.com/api/v2/test"

   constant(tag = "path:articles") with "articles"

   bind<NewsApi>() with factory { engine: HttpClientEngine ->
      NewsApi(clientEngine = engine)
   }

   bind<HttpClient>() with factory { engine: HttpClientEngine ->
      HttpClient(engine) {
         install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
               prettyPrint = true
               isLenient = true
               ignoreUnknownKeys = true
            })
         }
         install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
         }
      }
   }
}