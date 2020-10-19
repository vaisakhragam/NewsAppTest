package com.example.newsapp.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "allNews_entity")
data class AllNewsEntity(

        @PrimaryKey(autoGenerate = true)
        val id: Long=0,


        @ColumnInfo(name = "author")
        val author: String = "",

        @ColumnInfo(name = "title")
        val title: String = "",

        @ColumnInfo(name = "description")
        val description: String = "",

        @ColumnInfo(name = "url")
        val url: String = "",

        @ColumnInfo(name = "urlToImage")
        val urlToImage: String = "",

        @ColumnInfo(name = "publishedAt")
        val publishedAt: String = "",

        @ColumnInfo(name = "content")
        val content: String = "",
)