package com.solomon.topheadlinesnewsapp.ui.screen.articles

import android.content.Context
import com.solomon.topheadlinesnewsapp.model.Article
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsActivity

class ArticlesListNavigator(private val context: Context) {

    fun openArticleDetailsScreen(article: Article) {
        context.startActivity(ArticleDetailsActivity.newIntent(context, article))
    }
}