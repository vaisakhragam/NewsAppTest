package com.example.newsapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class News(


        @Expose
        @SerializedName("source")
        val source: Source,
        @Expose
        @SerializedName("author")
        val author: String = "",
        @Expose
        @SerializedName("title")
        val title: String = "",
        @Expose
        @SerializedName("description")
        val description: String = "",
        @Expose
        @SerializedName("url")
        val url: String = "",
        @Expose
        @SerializedName("urlToImage")
        val urlToImage: String = "",
        @Expose
        @SerializedName("publishedAt")
        val publishedAt: String = "",
        @Expose
        @SerializedName("content")
        val content: String = "",
)


data class NewsHolder
(
        @Expose
        @SerializedName("status")
        val status: String = "",
        @Expose
        @SerializedName("totalResults")
        val totalResults: Int = 0,
        @Expose
        @SerializedName("articles")
        val articles: ArrayList<News>
)

data class Source
(

        @Expose
        @SerializedName("id")
        val id: String = "",
        @Expose
        @SerializedName("name")
        val name: String = "",

        @Expose
        @SerializedName("description")
        val description: String = "",
        @Expose
        @SerializedName("url")
        val url: String = "",
        @Expose
        @SerializedName("category")
        val category: String = "",
        @Expose
        @SerializedName("language")
        val language: String = "",
        @Expose
        @SerializedName("country")
        val country: String = ""

)

data class SourceHolder(

        @Expose
        @SerializedName("id")
        val status: String = "",
        @Expose
        @SerializedName("sources")
        val sources: ArrayList<Source>

)