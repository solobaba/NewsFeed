package com.jothaen.jetpackcomposenewsapp.di

import com.jothaen.jetpackcomposenewsapp.ui.screen.details.ArticleDetailsNavigator
import com.jothaen.jetpackcomposenewsapp.ui.screen.details.ArticleDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleDetailsModule = module {
    factory { ArticleDetailsNavigator(get()) }
    viewModel { ArticleDetailsViewModel(get()) }
}