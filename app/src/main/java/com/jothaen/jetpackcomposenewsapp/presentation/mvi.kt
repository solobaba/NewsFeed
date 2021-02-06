package com.jothaen.jetpackcomposenewsapp.presentation

import com.jothaen.jetpackcomposenewsapp.presentation.model.Article

sealed class NewsScreenState {
    object InitState : NewsScreenState()
    object Loading : NewsScreenState()
    class Error(val errorMessage: String) : NewsScreenState()
    class Success(val articles: List<Article>) : NewsScreenState()
}

sealed class NewsIntent {
    object Load : NewsIntent()
    object Refresh : NewsIntent()
    object Retry : NewsIntent()
    class ShowDetails(val article: Article) : NewsIntent()
}
