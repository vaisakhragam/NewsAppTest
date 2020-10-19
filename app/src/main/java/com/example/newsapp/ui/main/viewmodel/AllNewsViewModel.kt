package com.example.newsapp.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.local.db.entities.AllNewsEntity
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Source
import com.example.newsapp.data.repository.APIRepository
import com.example.newsapp.data.repository.DBRepository
import com.example.newsapp.utils.NetworkHelper
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch

class AllNewsViewModel @ViewModelInject constructor(
    private val APIRepository: APIRepository,
    private val networkHelper: NetworkHelper, val dBRepository: DBRepository
) : ViewModel() {

    var count=0
    private val _allNews = MutableLiveData<Resource<List<News>>>()
    val allNews: LiveData<Resource<List<News>>>
        get() = _allNews

    init {


        count = 0
        viewModelScope.launch {
            count = dBRepository.getAllNewsount().toInt()
            println("DB count$count")
            if (count > 1) {


                println("after DB count$count")
                viewModelScope.launch {

                    var source: List<News> = dBRepository.getAllNews().map {
                        it.toNews()

                    }

                    _allNews.postValue(Resource.success(source))
                }


            } else {
                fetchAllNews("bitcoin")
            }
        }

    }

    private fun fetchAllNews(q: String) {
        viewModelScope.launch {
            _allNews.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                APIRepository.getAllNews(q, BuildConfig.APIKEY).let {
                    if (it.isSuccessful) {
                        println("data=${it.body()?.articles}")

                        insertToDB(it.body()?.articles)
                        _allNews.postValue(Resource.success(it.body()?.articles))


                    } else _allNews.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _allNews.postValue(Resource.error("No internet connection", null))
        }
    }


    fun insertToDB(source: ArrayList<News>?) {


        viewModelScope.launch {
            var source = source?.map {
                it.toAllNewsEntity()

            }?.map {
                dBRepository.insertAllNewsData(it)

            }
        }
    }


    fun AllNewsEntity.toNews() = News(

        source = Source(),
        author = author,
        title = title,

        url = url,
        description = description,

        urlToImage = urlToImage,

        publishedAt = publishedAt,
        content = content
    )

    fun News.toAllNewsEntity() = AllNewsEntity(


        author = author,
        title = title,

        url = url,
        description = description,

        urlToImage = urlToImage,

        publishedAt = publishedAt,
        content = content
    )
}