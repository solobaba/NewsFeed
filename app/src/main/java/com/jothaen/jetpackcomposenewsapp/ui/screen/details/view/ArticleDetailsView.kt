package com.jothaen.jetpackcomposenewsapp.ui.screen.details.view

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jothaen.jetpackcomposenewsapp.model.Article
import com.jothaen.jetpackcomposenewsapp.ui.component.Image


@Composable
fun ArticleDetailsView(article: Article, onReadAllClick: (articleUrl: String) -> Unit) {
    ScrollableColumn(
        modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.CenterStart),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(article.imageUrl, Modifier.fillMaxWidth().height(400.dp))
        ArticleDescription(article.description)
        ArticleContent(article.content)
        ReadAllButton { onReadAllClick.invoke(article.fullArticleUrl) }
    }
}

@Composable
private fun ArticleDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ArticleContent(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ReadAllButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = { Text(text = "Read full article") },
        modifier = Modifier.padding(all = 16.dp)
    )
}

