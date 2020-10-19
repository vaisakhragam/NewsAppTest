package com.example.newsapp.data.repository

import com.example.newsapp.data.local.db.dao.NewsDAO
import com.example.newsapp.data.local.db.entities.AllNewsEntity
import com.example.newsapp.data.local.db.entities.SourceEntity
import javax.inject.Inject


class DBRepository @Inject constructor(private val newsDAO: NewsDAO) {
    suspend fun insertSourceData(sourceEntity: SourceEntity) = newsDAO.insertSource(sourceEntity)

    suspend fun getSource() = newsDAO.getSource()
    suspend fun getSourceCount() = newsDAO.getSourceCount()

    suspend fun insertAllNewsData(allNewsEntity: AllNewsEntity) = newsDAO.insertAllNews(allNewsEntity)

    suspend fun getAllNews() = newsDAO.getAllNews()
    suspend fun getAllNewsount() = newsDAO.getAllNewsCount()

    suspend fun getAllNewDetail(title:String) = newsDAO.getNewsDetail(title)
}