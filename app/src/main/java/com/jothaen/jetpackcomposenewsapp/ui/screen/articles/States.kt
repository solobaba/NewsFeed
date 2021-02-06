package com.jothaen.jetpackcomposenewsapp.ui.screen.articles

import com.jothaen.jetpackcomposenewsapp.model.Article

sealed class ArticlesListState {
    object InitState : ArticlesListState()
    object Loading : ArticlesListState()
    class Error(val errorMessage: String) : ArticlesListState()
    class Success(val articles: List<Article>) : ArticlesListState()
}