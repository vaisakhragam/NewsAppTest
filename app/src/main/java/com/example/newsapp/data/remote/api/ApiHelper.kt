package com.example.newsapp.data.remote.api

import com.example.newsapp.data.model.NewsHolder
import com.example.newsapp.data.model.SourceHolder
import retrofit2.Response

interface ApiHelper {


    suspend fun getSources(apiKey:String): Response<SourceHolder>
    suspend fun getAllNews(country:String,apiKey:String): Response<NewsHolder>
    suspend fun getTopHeadLines(country:String,apiKey:String): Response<NewsHolder>
}