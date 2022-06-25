package com.solomon.topheadlinesnewsapp.di

import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsNavigator
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleDetailsModule = module {
    factory { ArticleDetailsNavigator(get()) }
    viewModel { ArticleDetailsViewModel(get(), get()) }
}