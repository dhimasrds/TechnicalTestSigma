package com.example.technicaltestsigma.pages.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltestsigma.model.model.everything.Article
import com.example.technicaltestsigma.repository.NewsSourcesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivityViewModel @ViewModelInject constructor(
    private val newsSourcesRepository: NewsSourcesRepository
) :ViewModel() {

    val _everything = MutableLiveData<List<Article>>()
    val everything: LiveData<List<Article>> get() = _everything

    val _progressbar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> get() = _progressbar

    val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getEverything(keyWord : String){

        viewModelScope.launch(Dispatchers.Default) {
            newsSourcesRepository.getEverything(keyWord).let {
                if (it.isSuccessful){
                    if(it.body() !=null){
                        _everything.postValue(it.body()!!.articles!!)
                    }
                    _progressbar.postValue(true)
                }else{
                    val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                    Log.d("statusCode message", (jsonObj.getString("message")))
                    _error.postValue(jsonObj.getString("message"))
                    _progressbar.postValue(true)

                }


            }
        }
    }
}