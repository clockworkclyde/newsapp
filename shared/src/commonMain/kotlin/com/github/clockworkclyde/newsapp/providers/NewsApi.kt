package com.github.clockworkclyde.newsapp.providers

import com.github.clockworkclyde.newsapp.di.kodein
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import org.kodein.di.instance

class NewsApi(clientEngine: HttpClientEngine) {

   private val httpClient: HttpClient by kodein.instance(arg = clientEngine)
   private val baseUrl: String by kodein.instance(tag = "newsBaseUrl")

   private val fetchUrlPath: String by kodein.instance(tag = "path:articles")

   @OptIn(ExperimentalSerializationApi::class)
   suspend fun fetchNewsList(page: Int? = null): FetchResponseModel {
      return httpClient.get<HttpResponse> {
         url {
            protocol = URLProtocol.HTTPS
            method = HttpMethod.Get
            host = baseUrl
            encodedPath = fetchUrlPath
            if (page != null) parameters.append("page", page.toString())
         }
      }.let { Json.decodeFromString<FetchResponseModel>(it.readText()) }
   }

}