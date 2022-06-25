package com.solomon.topheadlinesnewsapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.solomon.topheadlinesnewsapp.R
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Image(imageUrl: String, modifier: Modifier) {
    AsyncImage(
        model = imageUrl,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        placeholder = painterResource(R.drawable.ic_baseline_image)
    )
}