package com.jothaen.jetpackcomposenewsapp.di

import com.jothaen.jetpackcomposenewsapp.ui.NewsViewModel
import com.jothaen.jetpackcomposenewsapp.usecase.GetArticlesUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    viewModel { NewsViewModel(getArticlesUseCase = get()) }
    factory { GetArticlesUseCase(get()) }
}