package com.bashkir.a22bytenews.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/v2/"

val newsModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().run {
                    val url = url.newBuilder()
                        .addQueryParameter("apikey", "f82cf66378b042cda5518c2371c796af")
                        .build()
                    newBuilder().url(url).build()
                }
                chain.proceed(request)
            }
            .build()
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(NewsApi::class.java)
    }

    factory { params -> NewsViewModel(params.get(), get()) }
}