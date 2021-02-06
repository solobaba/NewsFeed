package com.jothaen.jetpackcomposenewsapp.presentation.model

import com.jothaen.jetpackcomposenewsapp.network.api.ArticleDto

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val content: String
)

fun ArticleDto.toModel(): Article =
    Article(
        author = author.orEmpty(),
        title = title,
        description = description.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        content = content.orEmpty()
    )