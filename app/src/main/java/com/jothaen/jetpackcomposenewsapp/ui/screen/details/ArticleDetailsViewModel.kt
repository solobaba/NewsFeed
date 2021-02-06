package com.jothaen.jetpackcomposenewsapp.ui.screen.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jothaen.jetpackcomposenewsapp.ui.screen.details.ArticleDetailsIntent.*
import com.jothaen.jetpackcomposenewsapp.ui.screen.details.ArticleDetailsState.Loading

class ArticleDetailsViewModel(
    private val navigator: ArticleDetailsNavigator
) : ViewModel() {

    val state: MutableLiveData<ArticleDetailsState> = MutableLiveData(Loading)

    fun processIntent(intent: ArticleDetailsIntent) {
        when(intent) {
            is Show -> emitState(ArticleDetailsState.Success(intent.article))
            is OpenInBrowser -> navigator.openInBrowser(intent.articleUrl)
        }
    }

    private fun emitState(state: ArticleDetailsState) {
        this.state.value = state
    }
}
