package com.example.newsapp.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.local.db.entities.AllNewsEntity
import com.example.newsapp.data.repository.APIRepository
import com.example.newsapp.data.repository.DBRepository
import com.example.newsapp.utils.NetworkHelper
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val APIRepository: APIRepository,
    private val networkHelper: NetworkHelper, private val dbRepository: DBRepository
) : ViewModel() {

    private val _allNews = MutableLiveData<AllNewsEntity>()
    private val title: MutableLiveData<String> = MutableLiveData<String>()
    val allNews: LiveData<AllNewsEntity>
        get() = _allNews

   /* init {
        //fetchDetail("bitcoin")
    }*/

    private fun fetchDetail(title: String) {
        viewModelScope.launch {
           _allNews.postValue(dbRepository.getAllNewDetail(title))
        }
    }


    fun setTitle(title:String)
    {
        fetchDetail(title)
    }



}