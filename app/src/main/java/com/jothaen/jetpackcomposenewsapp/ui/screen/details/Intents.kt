package com.jothaen.jetpackcomposenewsapp.ui.screen.details

import com.jothaen.jetpackcomposenewsapp.model.Article

sealed class ArticleDetailsIntent {
    class Show(val article: Article) : ArticleDetailsIntent()
    class OpenInBrowser(val articleUrl: String) : ArticleDetailsIntent()
}