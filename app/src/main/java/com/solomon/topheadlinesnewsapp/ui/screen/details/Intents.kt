package com.solomon.topheadlinesnewsapp.ui.screen.details

import com.solomon.topheadlinesnewsapp.model.Article

sealed class ArticleDetailsIntent {
    class Show(val article: Article) : ArticleDetailsIntent()
    class OpenInBrowser(val articleUrl: String) : ArticleDetailsIntent()
}