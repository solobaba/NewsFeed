package com.jothaen.jetpackcomposenewsapp.usecase

import com.jothaen.jetpackcomposenewsapp.network.api.ArticleDto
import com.jothaen.jetpackcomposenewsapp.network.repository.NewsRepository
import com.jothaen.jetpackcomposenewsapp.presentation.model.Article
import com.jothaen.jetpackcomposenewsapp.presentation.model.toModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GetArticlesUseCase(private val newsRepository: NewsRepository) {

    fun get(): Single<List<Article>> =
        newsRepository
            .getTopHeadlines(countryCode = "pl")
            .flatMap { Observable.fromIterable(it) }
            .filter(::filterInvalidArticles)
            .map { it.toModel() }
            .toList()


    private fun filterInvalidArticles(articleDto: ArticleDto): Boolean = with(articleDto) {
        return author != null && urlToImage != null && description != null && content != null
    }

}