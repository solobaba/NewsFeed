package com.jothaen.jetpackcomposenewsapp.ui.screen.articles

import com.jothaen.jetpackcomposenewsapp.model.Article


sealed class ArticlesListIntent {
    object Fetch : ArticlesListIntent()
    object Refresh : ArticlesListIntent()
    object Retry : ArticlesListIntent()
    class ShowDetails(val article: Article) : ArticlesListIntent()
}