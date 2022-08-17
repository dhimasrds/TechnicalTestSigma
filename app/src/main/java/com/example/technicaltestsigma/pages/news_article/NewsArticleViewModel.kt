package com.example.technicaltestsigma.pages.news_article

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltestsigma.model.model.article.Article
import com.example.technicaltestsigma.repository.NewsSourcesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class NewsArticleViewModel @ViewModelInject constructor(
    private val newsSourcesRepository: NewsSourcesRepository
) : ViewModel() {

    val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>> get() = _article

    val _emptyArticle = MutableLiveData<Boolean>()
    val emptyArticle : LiveData<Boolean> get() = _emptyArticle

    val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getArticleBySource(source: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsSourcesRepository.getArticelBySource(source).let {
                val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                if (it.code() ==200) {
                    _article.postValue(it.body()!!.articles!!)
                    if (it.body()!!.totalResults == 0) {
                        _emptyArticle.postValue(true)
                    }
                }else
                    _error.postValue(jsonObj.getString("message"))
            }
        }
    }
}