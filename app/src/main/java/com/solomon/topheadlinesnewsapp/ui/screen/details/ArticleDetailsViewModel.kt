package com.solomon.topheadlinesnewsapp.ui.screen.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.solomon.topheadlinesnewsapp.network.repository.NewsRepository
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsIntent.*
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsState.Loading

class ArticleDetailsViewModel(
    private val navigator: ArticleDetailsNavigator,
    repository: NewsRepository,
) : ViewModel() {
    val news = repository.getNews().asLiveData()

    val state: LiveData<ArticleDetailsState>
        get() = _state

    private val _state: MutableLiveData<ArticleDetailsState> = MutableLiveData(Loading)

    fun processIntent(intent: ArticleDetailsIntent) {
        when (intent) {
            is Show -> emitState(ArticleDetailsState.Success(intent.article))
            is OpenInBrowser -> navigator.openInBrowser(intent.articleUrl)
        }
    }

    private fun emitState(state: ArticleDetailsState) {
        _state.value = state
    }
}