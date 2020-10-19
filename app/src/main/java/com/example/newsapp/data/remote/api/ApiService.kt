package com.example.newsapp.data.remote.api

import com.example.newsapp.data.model.NewsHolder
import com.example.newsapp.data.model.SourceHolder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("sources")
    suspend fun getSources(
                                @Query("apiKey") apiKey: String,): Response<SourceHolder>
    @GET("everything")
    suspend fun getAllNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String,): Response<NewsHolder>
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String,
                                @Query("apiKey") apiKey: String,): Response<NewsHolder>



}