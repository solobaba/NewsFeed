package com.jothaen.jetpackcomposenewsapp.usecase

import com.jothaen.jetpackcomposenewsapp.network.api.Article
import com.jothaen.jetpackcomposenewsapp.network.repository.NewsRepository
import io.reactivex.rxjava3.core.Single

class GetArticlesUseCase(private val newsRepository: NewsRepository) {

    fun get(): Single<List<Article>> =
        newsRepository
            .getTopHeadlines("us")
            .map { it.articles }
}