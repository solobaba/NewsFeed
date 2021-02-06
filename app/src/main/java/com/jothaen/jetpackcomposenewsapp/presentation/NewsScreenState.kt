package com.jothaen.jetpackcomposenewsapp.presentation

import com.jothaen.jetpackcomposenewsapp.network.api.Article

sealed class NewsScreenState  {
    object InitState: NewsScreenState()
    object Loading : NewsScreenState()
    class Error(val errorMessage: String): NewsScreenState()
    class Success(val articles: List<Article>) : NewsScreenState()
}

sealed class NewsIntent {
    object Load : NewsIntent()
    object Retry : NewsIntent()
    class ShowDetails(val article: Article): NewsIntent()
}
