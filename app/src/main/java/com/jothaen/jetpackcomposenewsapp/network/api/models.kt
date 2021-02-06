package com.jothaen.jetpackcomposenewsapp.network.api

data class ArticlesResponseDto(
    val totalArticles: Int,
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val content: String?
)