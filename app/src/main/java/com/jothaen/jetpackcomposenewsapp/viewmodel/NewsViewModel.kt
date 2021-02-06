package com.jothaen.jetpackcomposenewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState
import com.jothaen.jetpackcomposenewsapp.usecase.GetArticlesUseCase
import com.jothaen.jetpackcomposenewsapp.util.applyStandardSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class NewsViewModel(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    val state = MutableLiveData<NewsScreenState>(NewsScreenState.InitState)

    private val disposables = CompositeDisposable()

    fun processIntent(intent: NewsIntent) {
        when (intent) {
            is NewsIntent.Load -> loadArticles()
            is NewsIntent.Retry -> loadArticles()
            is NewsIntent.ShowDetails -> {
                // TODO
            }
        }
    }

    private fun loadArticles() {
        getArticlesUseCase.get()
            .applyStandardSchedulers()
            .doOnSubscribe { emitState(NewsScreenState.Loading) }
            .subscribeBy(
                onSuccess = { emitState(NewsScreenState.Success(it)) },
                onError = { emitState(NewsScreenState.Error("Error occurred")) }
            )
            .addTo(disposables)
    }

    private fun emitState(state: NewsScreenState) {
        this.state.value = state
    }
}
