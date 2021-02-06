package com.jothaen.jetpackcomposenewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jothaen.jetpackcomposenewsapp.presentation.NewsIntent
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState
import com.jothaen.jetpackcomposenewsapp.presentation.NewsScreenState.*
import com.jothaen.jetpackcomposenewsapp.usecase.GetArticlesUseCase
import com.jothaen.jetpackcomposenewsapp.util.applyStandardSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class NewsViewModel(private val getArticlesUseCase: GetArticlesUseCase) : ViewModel() {

    val state = MutableLiveData<NewsScreenState>(InitState)

    private val disposables = CompositeDisposable()

    fun processIntent(intent: NewsIntent) {
        when (intent) {
            is NewsIntent.Load, NewsIntent.Refresh, NewsIntent.Retry -> loadArticles()
            is NewsIntent.ShowDetails -> {
               // TODO - implement navigation to details screen
            }
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun loadArticles() {
        getArticlesUseCase.get()
            .applyStandardSchedulers()
            .doOnSubscribe { emitState(Loading) }
            .subscribeBy(
                onSuccess = { emitState(Success(it)) },
                onError = { emitState(Error("Something went wrong.")) }
            )
            .addTo(disposables)
    }

    private fun emitState(state: NewsScreenState) {
        this.state.value = state
    }
}
