package com.jothaen.jetpackcomposenewsapp.ui

import android.app.Application
import com.jothaen.jetpackcomposenewsapp.di.articleDetailsModule
import com.jothaen.jetpackcomposenewsapp.di.articlesListModule
import com.jothaen.jetpackcomposenewsapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApp)
            modules(networkModule, articlesListModule, articleDetailsModule)
        }
    }
}