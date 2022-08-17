package com.example.technicaltestsigma.pages.news_sources

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltestsigma.model.model.article.Article
import com.example.technicaltestsigma.model.model.sources.Source
import com.example.technicaltestsigma.model.model.sources.Sources
import com.example.technicaltestsigma.repository.NewsSourcesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class NewsSourcesViewModel @ViewModelInject constructor(
    private val newsSourcesRepository: NewsSourcesRepository
) : ViewModel() {

    val _sources = MutableLiveData<List<Source>>()
    val sources: LiveData<List<Source>> get() = _sources

    val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>> get() = _article

    val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun getSourcesByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsSourcesRepository.getSources(category).let {
                val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                Log.d("statusCode", it.code().toString())
                Log.d("statusCode message", (jsonObj.getString("message")))
                if (it.code() ==200)
                _sources.postValue(it.body()!!.sources!!)
                else
                    _error.postValue(jsonObj.getString("message"))
            }
        }
    }
}