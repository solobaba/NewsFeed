package com.jothaen.jetpackcomposenewsapp.model

import android.os.Parcelable

import com.jothaen.jetpackcomposenewsapp.network.api.ArticleDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val content: String,
    val fullArticleUrl: String
) : Parcelable

fun ArticleDto.toModel(): Article =
    Article(
        author = author.orEmpty(),
        title = title,
        description = description.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        content = content.orEmpty(),
        fullArticleUrl = url
    )