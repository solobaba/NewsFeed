package com.solomon.topheadlinesnewsapp.di

import androidx.room.Room
import com.solomon.topheadlinesnewsapp.data.NewsListDatabase
import com.solomon.topheadlinesnewsapp.network.api.NewsApi
import com.solomon.topheadlinesnewsapp.network.repository.NewsRepository
import com.solomon.topheadlinesnewsapp.ui.screen.articles.ArticlesListViewModel
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsViewModel
import com.solomon.topheadlinesnewsapp.usecase.GetArticlesUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val database = module {
    single {
        Room.databaseBuilder(androidApplication(), NewsListDatabase::class.java, "NewsList.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        GetArticlesUseCase(get())
    }
}

val viewModel = module {
    viewModel {
        ArticlesListViewModel(get())
    }

//    viewModel {
//        ArticleDetailsViewModel(get(), get())
//    }
}