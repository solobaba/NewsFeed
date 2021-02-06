package com.jothaen.jetpackcomposenewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.jothaen.jetpackcomposenewsapp.R
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent.*
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState.*
import com.jothaen.jetpackcomposenewsapp.ui.component.common.ErrorView
import com.jothaen.jetpackcomposenewsapp.ui.component.common.LoadingView
import com.jothaen.jetpackcomposenewsapp.ui.component.articleslist.ArticlesList
import com.jothaen.jetpackcomposenewsapp.ui.component.common.Toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postIntent(Load)

        setContent {
            MaterialTheme {
                val state = viewModel.state.observeAsState()

                Column {
                    Toolbar()
                    Render(state.value)
                }

            }
        }
    }

    @Composable
    private fun Render(state: NewsScreenState?) {
        when (state) {
            is Loading -> LoadingView()
            is Success -> ArticlesList(state.articles) { postIntent(ShowDetails(it)) }
            is Error -> ErrorView(state.errorMessage) { postIntent(Retry) }
        }
    }

    @Composable
    private fun Toolbar() {
        Toolbar(
            title = getString(R.string.app_name),
            rightIcon = Icons.Default.Refresh,
            onRightIconClick = { postIntent(Refresh) }
        )
    }

    private fun postIntent(intent: NewsIntent) {
        viewModel.processIntent(intent)
    }
}
