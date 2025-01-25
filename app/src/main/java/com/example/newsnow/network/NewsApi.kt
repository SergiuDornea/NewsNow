package com.example.newsnow.network

import com.example.newsnow.BuildConfig
import com.example.newsnow.network.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse
}