package com.bashkir.a22bytenews.data

import com.bashkir.a22bytenews.data.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getArticles(@Query("q") query: String): Response

    @GET("top-headlines")
    suspend fun getTopArticles(@Query("sources") sources : String = "bbc-news,google-news-ru"): Response
}