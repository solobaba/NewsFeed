package com.jothaen.jetpackcomposenewsapp.network.api

data class ArticlesResponse(
    val totalArticles: Int,
    val articles: List<Article>
)

data class Article(
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val content: String?
)