package com.jothaen.jetpackcomposenewsapp.ui.screen.details

import com.jothaen.jetpackcomposenewsapp.model.Article

sealed class ArticleDetailsState {
    object Loading : ArticleDetailsState()
    class Success(val article: Article) : ArticleDetailsState()
}