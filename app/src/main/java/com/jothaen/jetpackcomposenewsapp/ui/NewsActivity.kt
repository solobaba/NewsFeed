package com.jothaen.jetpackcomposenewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.jothaen.jetpackcomposenewsapp.presentation.Contract
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent.*
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState.*
import com.jothaen.jetpackcomposenewsapp.ui.component.ErrorView
import com.jothaen.jetpackcomposenewsapp.ui.component.LoadingView
import com.jothaen.jetpackcomposenewsapp.ui.component.ArticlesList
import org.koin.android.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity(), Contract.View {

    private val viewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postIntent(Load)

        setContent {
            MaterialTheme {
                val state = viewModel.state.observeAsState()
                state.value?.let { render(it) }
            }
        }
    }

    @Composable
    override fun render(state: NewsScreenState) {
        when (state) {
            is Loading -> LoadingView()
            is Success -> ArticlesList(state.articles) { postIntent(ShowDetails(it)) }
            is Error -> ErrorView(state.errorMessage) { postIntent(Retry) }
        }
    }

    private fun postIntent(intent: NewsIntent) {
        viewModel.processIntent(intent)
    }
}
