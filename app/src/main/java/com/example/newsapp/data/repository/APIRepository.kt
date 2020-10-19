package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.api.ApiHelper
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiHelper: ApiHelper) {


    suspend fun getSources(apiKey:String) =  apiHelper.getSources(apiKey)
    suspend fun getAllNews(q:String,apiKey:String) =  apiHelper.getAllNews(q,apiKey)
    suspend fun getTopHeadlines(country:String,apiKey:String) =  apiHelper.getTopHeadLines(country,apiKey)




}