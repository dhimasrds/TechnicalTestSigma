package com.example.technicaltestsigma.api

import javax.inject.Inject

class NewsApiClient  @Inject constructor(
    private val newsApiInterface: NewsApiService
){
    suspend fun getEverything(keyWord : String, apiKey : String) = newsApiInterface.getEverything(keyWord,apiKey)

    suspend fun getSourcesByCategory(category : String, apiKey : String) = newsApiInterface.getSourcesByCategory(category,apiKey)

    suspend fun getArticelByCategory(source : String, apiKey : String) = newsApiInterface.getArticelByCategory(source,apiKey)
}