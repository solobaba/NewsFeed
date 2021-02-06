package com.jothaen.jetpackcomposenewsapp.di

import com.jothaen.jetpackcomposenewsapp.network.api.NewsApi
import com.jothaen.jetpackcomposenewsapp.network.repository.NewsRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val API_URL = "http://newsapi.org/v2/"
private const val API_KEY = "7fa6727ed50442d48236c75e4f3e705c"

val networkModule = module {
    factory<Retrofit> {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(API_URL)
            .build()
    }

    factory<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }
    factory { NewsRepository(get(), API_KEY) }
}