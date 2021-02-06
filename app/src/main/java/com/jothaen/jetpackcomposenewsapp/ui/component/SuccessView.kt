package com.jothaen.jetpackcomposenewsapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SuccessView(articlesCount: Int) {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Text("Fetched $articlesCount articles.")
    }
}