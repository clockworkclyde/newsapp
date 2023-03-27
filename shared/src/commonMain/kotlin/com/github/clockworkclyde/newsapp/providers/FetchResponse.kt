package com.github.clockworkclyde.newsapp.providers

import kotlinx.serialization.SerialName

// Response Structure

@kotlinx.serialization.Serializable
data class FetchResponseModel(
   @SerialName("error") val error: Int?,
   @SerialName("data") val responseData: FetchResponseData
)

@kotlinx.serialization.Serializable
data class FetchResponseData(
   @SerialName("counts") val counts: Int,
   @SerialName("pagination") val pagination: FetchResponseDataPagination,
   @SerialName("result") val result: List<TypedResponse>
)

@kotlinx.serialization.Serializable
data class FetchResponseDataPagination(
   @SerialName("page_size") val size: Int,
   @SerialName("current_page") val current: Int
)