package com.jothaen.jetpackcomposenewsapp.di

import com.jothaen.jetpackcomposenewsapp.ui.screen.articles.ArticlesListNavigator
import com.jothaen.jetpackcomposenewsapp.ui.screen.articles.ArticlesListViewModel
import com.jothaen.jetpackcomposenewsapp.usecase.GetArticlesUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articlesListModule = module {
    factory { GetArticlesUseCase(get()) }
    factory { ArticlesListNavigator(get())}
    viewModel { ArticlesListViewModel(getArticlesUseCase = get(), get()) }
}