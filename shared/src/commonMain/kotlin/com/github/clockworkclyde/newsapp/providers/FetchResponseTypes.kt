package com.github.clockworkclyde.newsapp.providers

import com.github.clockworkclyde.newsapp.common.IConvertableTo
import com.github.clockworkclyde.newsapp.domain.model.news.Article
import com.github.clockworkclyde.newsapp.domain.model.news.Banner
import com.github.clockworkclyde.newsapp.domain.model.news.NewsContentItem
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.*

// Fetch articles content types

@kotlinx.serialization.Serializable(with = TypedResponseSerializer::class)
sealed interface TypedResponse : IConvertableTo<NewsContentItem> {
   @SerialName("view_type")
   val contentType: Int

   override fun convertTo(): NewsContentItem
}

@kotlinx.serialization.Serializable
data class BannerResponse(
   @SerialName("view_type") override val contentType: Int,
   @SerialName("banner") val banner: Banner
) : TypedResponse {

   override fun convertTo(): Banner {
      return banner
   }
}

@kotlinx.serialization.Serializable
data class ArticleResponse(
   @SerialName("view_type") override val contentType: Int,
   @SerialName("article") val article: Article
) : TypedResponse {

   override fun convertTo(): Article {
      return article
   }
}

const val articleType = 0
const val staticBannerType = 5

object TypedResponseSerializer :
   JsonContentPolymorphicSerializer<TypedResponse>(TypedResponse::class) {

   override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out TypedResponse> {
      return when (val type = element.jsonObject["view_type"]?.jsonPrimitive?.intOrNull) {
         articleType -> ArticleResponse.serializer()
         staticBannerType -> BannerResponse.serializer()
         else -> error("Unknown content type : $type, is not implemented yet")
      }
   }
}