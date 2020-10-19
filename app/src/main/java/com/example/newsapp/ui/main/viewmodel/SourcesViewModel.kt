package com.example.newsapp.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.local.db.entities.SourceEntity
import com.example.newsapp.data.model.Source
import com.example.newsapp.data.repository.APIRepository
import com.example.newsapp.data.repository.DBRepository
import com.example.newsapp.utils.NetworkHelper
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch

class SourcesViewModel @ViewModelInject constructor(
    private val APIRepository: APIRepository,
    private val networkHelper: NetworkHelper,
    private val dbRepository: DBRepository,
) : ViewModel() {
 var count=0
    private val _sources = MutableLiveData<Resource<List<Source>>>()
    val sources: LiveData<Resource<List<Source>>>
        get() = _sources

    init {

         count = 0
        viewModelScope.launch {
            count = dbRepository.getSourceCount().toInt()
            println("DB count$count")
            if (count > 1) {


                println("after DB count$count")
                viewModelScope.launch {

                    var source: List<Source> = dbRepository.getSource().map {
                        it.toSource()

                    }

                    _sources.postValue(Resource.success(source))
                }


            } else {
                fetchSources()
            }
        }



    }

    private fun fetchSources() {
        viewModelScope.launch {
            _sources.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                APIRepository.getSources(BuildConfig.APIKEY).let {
                    if (it.isSuccessful) {
                        println("data=${it.body()?.sources}")

                        insertToDB(it.body()?.sources)


                        _sources.postValue(Resource.success(it.body()?.sources))


                    } else _sources.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _sources.postValue(Resource.error("No internet connection", null))
        }
    }


    fun insertToDB(source: ArrayList<Source>?) {


        viewModelScope.launch {
            var source = source?.map {
                it.toSourceEntity()

            }?.map {
                dbRepository.insertSourceData(it)

            }
        }
    }


    fun SourceEntity.toSource() = Source(

        id = id,
        name = name,
        description = description,

        url = url,
        category = category,

        language = language,

        country = country

    )

    fun Source.toSourceEntity() = SourceEntity(

        id = id,
        name = name,
        description = description,

        url = url,
        category = category,

        language = language,

        country = country

    )
}