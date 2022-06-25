package com.solomon.topheadlinesnewsapp.ui.screen.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solomon.topheadlinesnewsapp.data.NewsList
import com.solomon.topheadlinesnewsapp.ui.screen.articles.ArticlesListIntent.*
import com.solomon.topheadlinesnewsapp.ui.screen.articles.ArticlesListState.*
import com.solomon.topheadlinesnewsapp.usecase.GetArticlesUseCase
import com.solomon.topheadlinesnewsapp.util.applyStandardSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArticlesListViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    //private val navigator: ArticlesListNavigator
) : ViewModel() {

    val state: LiveData<ArticlesListState>
        get() = _state

    private val _state = MutableLiveData<ArticlesListState>(InitState)

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    private val _data = MutableStateFlow<Pair<List<NewsList>, String?>>(Pair(emptyList(), null))
    val data = _data.asStateFlow()

    private val disposables = CompositeDisposable()

    fun processIntent(intent: ArticlesListIntent) {
//        when (intent) {
//            is Fetch, Refresh, Retry -> loadArticles()
//            is ShowDetails -> navigator.openArticleDetailsScreen(intent.article)
//        }
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

    fun getNews() {
        viewModelScope.launch {
            getArticlesUseCase.getNews()
                .onStart {
                    _loading.value = true
                }.catch { e: Throwable ->
                    setNewsData(message = e.message ?: "Something went wrong.")
                }.collectLatest {
                    setNewsData(it)
                    Log.e("Articles", it.toString())
                }
        }
    }

    private fun setNewsData(newsList: List<NewsList> = emptyList(), message: String? = null) {
        _data.value = Pair(newsList, message)
        _loading.value = false
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}