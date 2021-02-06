package com.jothaen.jetpackcomposenewsapp.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/top-headlines")
    fun getTopHeadlines(
        @Query("country") countryCode: String,
        @Query("apiKey") apiKey: String
    ) : Single<ArticlesResponse>
}