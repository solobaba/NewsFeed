package com.solomon.topheadlinesnewsapp.ui.screen.articles

import com.solomon.topheadlinesnewsapp.model.Article

sealed class ArticlesListState {
    object InitState : ArticlesListState()
    object Loading : ArticlesListState()
    class Error(val errorMessage: String) : ArticlesListState()
    class Success(val articles: List<Article>) : ArticlesListState()
}