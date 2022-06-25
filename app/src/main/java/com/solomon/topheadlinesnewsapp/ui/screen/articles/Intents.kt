package com.solomon.topheadlinesnewsapp.ui.screen.articles

import com.solomon.topheadlinesnewsapp.model.Article

sealed class ArticlesListIntent {
    object Fetch : ArticlesListIntent()
    object Refresh : ArticlesListIntent()
    object Retry : ArticlesListIntent()
    class ShowDetails(val article: Article) : ArticlesListIntent()
}