package com.jothaen.jetpackcomposenewsapp.ui.component.articleslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jothaen.jetpackcomposenewsapp.presentation.model.Article
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ArticlesList(articles: List<Article>, onClick: (Article) -> Unit) {
    LazyColumn {
        itemsIndexed(articles) { _, article -> ArticleItem(article, onClick) }
    }
}

@Composable
private fun ArticleItem(article: Article, onClick: (Article) -> Unit) =
    Card(Modifier.fillMaxWidth().padding(8.dp), elevation = 8.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterStart)
                .clickable(onClick = { onClick.invoke(article) })
        ) {
            ArticleImage(article.imageUrl)
            ArticleTitle(article.title)
            ArticleAuthor(article.author)
        }
    }

@Composable
private fun ArticleImage(imageUrl: String) {
    CoilImage(
        data = imageUrl,
        loading = { ImagePlaceholder() },
        error = { ImagePlaceholder() },
        modifier = Modifier.height(200.dp).fillMaxWidth(),
        contentScale = ContentScale.Crop,
        fadeIn = true
    )
}

@Composable
private fun ImagePlaceholder() {
    Box(Modifier.fillMaxWidth().background(Color.Gray))
}

@Composable
private fun ArticleTitle(title: String) =
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(horizontal = 8.dp)
    )

@Composable
private fun ArticleAuthor(author: String) =
    Text(
        text = author,
        style = MaterialTheme.typography.body1,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    )