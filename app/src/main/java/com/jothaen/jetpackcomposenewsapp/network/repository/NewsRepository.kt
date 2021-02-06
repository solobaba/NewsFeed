package com.jothaen.jetpackcomposenewsapp.network.repository

import com.jothaen.jetpackcomposenewsapp.network.api.ArticlesResponse
import com.jothaen.jetpackcomposenewsapp.network.api.NewsApi
import io.reactivex.rxjava3.core.Single

class NewsRepository(private val newsApi: NewsApi, private val apiKey: String) {

    fun getTopHeadlines(countryCode: String): Single<ArticlesResponse> =
        newsApi.getTopHeadlines(countryCode, apiKey)
}