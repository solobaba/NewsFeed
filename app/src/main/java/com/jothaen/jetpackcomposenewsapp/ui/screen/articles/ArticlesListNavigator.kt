package com.jothaen.jetpackcomposenewsapp.ui.screen.articles

import android.content.Context
import com.jothaen.jetpackcomposenewsapp.model.Article
import com.jothaen.jetpackcomposenewsapp.ui.screen.details.ArticleDetailsActivity

class ArticlesListNavigator(private val context: Context) {

    fun openArticleDetailsScreen(article: Article) {
        context.startActivity(ArticleDetailsActivity.newIntent(context, article))
    }
}