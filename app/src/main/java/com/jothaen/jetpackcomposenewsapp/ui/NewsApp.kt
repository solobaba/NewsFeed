package com.jothaen.jetpackcomposenewsapp.ui

import android.app.Application
import com.jothaen.jetpackcomposenewsapp.di.networkModule
import com.jothaen.jetpackcomposenewsapp.di.newsModule
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, newsModule)
        }
    }
}