package com.jothaen.jetpackcomposenewsapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val colors = lightColors(
        primary = purple500,
        primaryVariant = purple700,
        secondary = teal200
)

@Composable
fun NewsAppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}