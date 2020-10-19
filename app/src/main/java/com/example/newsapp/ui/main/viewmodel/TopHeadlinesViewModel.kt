package com.example.newsapp.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.News
import com.example.newsapp.data.repository.APIRepository
import com.example.newsapp.utils.NetworkHelper
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch

class TopHeadlinesViewModel @ViewModelInject constructor(
        private val APIRepository: APIRepository,
        private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _news = MutableLiveData<Resource<List<News>>>()
    val news: LiveData<Resource<List<News>>>
        get() = _news

    init {
        fetchTopNews("us")
    }

    private fun fetchTopNews(country:String) {
        viewModelScope.launch {
            _news.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                APIRepository.getTopHeadlines(country,BuildConfig.APIKEY).let {
                    if (it.isSuccessful) {
                        _news.postValue(Resource.success(it.body()?.articles))
                    } else _news.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _news.postValue(Resource.error("No internet connection", null))
        }
    }
}