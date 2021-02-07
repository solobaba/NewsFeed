package com.jothaen.jetpackcomposenewsapp.ui.screen.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jothaen.jetpackcomposenewsapp.ui.screen.articles.ArticlesListIntent.*
import com.jothaen.jetpackcomposenewsapp.ui.screen.articles.ArticlesListState.*
import com.jothaen.jetpackcomposenewsapp.usecase.GetArticlesUseCase
import com.jothaen.jetpackcomposenewsapp.util.applyStandardSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy


class ArticlesListViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val navigator: ArticlesListNavigator
) : ViewModel() {

    val state: LiveData<ArticlesListState>
        get() = _state

    private val _state = MutableLiveData<ArticlesListState>(InitState)

    private val disposables = CompositeDisposable()

    fun processIntent(intent: ArticlesListIntent) {
        when (intent) {
            is Fetch, Refresh, Retry -> loadArticles()
            is ShowDetails -> navigator.openArticleDetailsScreen(intent.article)
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

    private fun emitState(state: ArticlesListState) {
        _state.value = state
    }
}
