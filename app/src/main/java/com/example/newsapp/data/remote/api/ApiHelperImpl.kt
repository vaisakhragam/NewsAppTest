package com.example.newsapp.data.remote.api

import com.example.newsapp.data.model.NewsHolder
import com.example.newsapp.data.model.SourceHolder
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getSources(apiKey: String): Response<SourceHolder> =apiService.getSources(apiKey)


    override suspend fun getAllNews(q:String,apiKey: String): Response<NewsHolder> =apiService.getAllNews(q,apiKey)
    override suspend fun getTopHeadLines(country:String,apiKey:String): Response<NewsHolder> = apiService.getTopHeadlines(country,apiKey )

}