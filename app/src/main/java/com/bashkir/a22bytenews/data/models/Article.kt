package com.bashkir.a22bytenews.data.models

import com.bashkir.a22bytenews.utils.LocalDateTimeJsonAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Article(
    val author: String?,
    val title: String,
    val description: String,
    @SerializedName("urlToImage") val img: String?,
    val url: String,
    @JsonAdapter(LocalDateTimeJsonAdapter::class)
    val publishedAt: LocalDateTime
)
