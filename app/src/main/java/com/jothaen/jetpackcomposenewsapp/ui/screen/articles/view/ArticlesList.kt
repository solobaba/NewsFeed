package com.jothaen.jetpackcomposenewsapp.ui.screen.articles.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jothaen.jetpackcomposenewsapp.model.Article
import com.jothaen.jetpackcomposenewsapp.ui.component.Image
import com.jothaen.jetpackcomposenewsapp.ui.component.ImagePlaceholder
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
            Image(article.imageUrl, Modifier.height(200.dp).fillMaxWidth())
            ArticleTitle(article.title)
            ArticleAuthor(article.author)
        }
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