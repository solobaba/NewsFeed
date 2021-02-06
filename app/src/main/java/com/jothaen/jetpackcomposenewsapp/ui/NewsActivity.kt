package com.jothaen.jetpackcomposenewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState
import com.jothaen.jetpackcomposenewsapp.ui.component.ErrorView
import com.jothaen.jetpackcomposenewsapp.ui.component.LoadingView
import com.jothaen.jetpackcomposenewsapp.ui.component.SuccessView
import com.jothaen.jetpackcomposenewsapp.ui.theme.NewsAppTheme
import com.jothaen.jetpackcomposenewsapp.viewmodel.NewsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postIntent(NewsIntent.Load)

        setContent {
            NewsAppTheme {
                val state = viewModel.state.observeAsState()
                render(state.value)
            }
        }
    }

    @Composable
    private fun render(state: NewsScreenState?) {
        when (state) {
            is NewsScreenState.Loading -> LoadingView()
            is NewsScreenState.Error -> ErrorView(state.errorMessage) { postIntent(NewsIntent.Retry) }
            is NewsScreenState.Success -> SuccessView(articlesCount = state.articles.size)
        }
    }

    private fun postIntent(intent: NewsIntent) {
        viewModel.processIntent(intent)
    }
}
