package com.jothaen.jetpackcomposenewsapp.network.repository

import com.jothaen.jetpackcomposenewsapp.network.api.ArticleDto
import com.jothaen.jetpackcomposenewsapp.network.api.NewsApi
import io.reactivex.rxjava3.core.Observable

class NewsRepository(private val newsApi: NewsApi, private val apiKey: String) {

    fun getTopHeadlines(countryCode: String): Observable<List<ArticleDto>> =
        newsApi
            .getTopHeadlines(countryCode, apiKey)
            .map { it.articles }
}