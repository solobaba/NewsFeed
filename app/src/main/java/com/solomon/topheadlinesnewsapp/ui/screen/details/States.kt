package com.solomon.topheadlinesnewsapp.ui.screen.details

import com.solomon.topheadlinesnewsapp.model.Article

sealed class ArticleDetailsState {
    object Loading : ArticleDetailsState()
    class Success(val article: Article) : ArticleDetailsState()
}