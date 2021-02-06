package com.jothaen.jetpackcomposenewsapp.presentation

import com.jothaen.jetpackcomposenewsapp.presentation.model.Article

interface Contract {

    interface ViewModel {
        fun processIntent(intent: NewsIntent)
    }

    interface View {
        fun render(state: NewsScreenState)
    }
}

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
