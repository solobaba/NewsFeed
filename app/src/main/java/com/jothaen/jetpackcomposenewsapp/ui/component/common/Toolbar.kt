package com.jothaen.jetpackcomposenewsapp.ui.component.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Toolbar(title: String, rightIcon: ImageVector, onRightIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(
                onClick = { onRightIconClick.invoke() },
                content = { Icon(rightIcon) }
            )
        }
    )
}