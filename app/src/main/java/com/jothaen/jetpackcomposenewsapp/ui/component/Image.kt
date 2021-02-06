package com.jothaen.jetpackcomposenewsapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Image(imageUrl: String, modifier: Modifier) {
    CoilImage(
        data = imageUrl,
        loading = { ImagePlaceholder() },
        error = { ImagePlaceholder() },
        modifier = modifier,
        contentScale = ContentScale.Crop,
        fadeIn = true
    )
}