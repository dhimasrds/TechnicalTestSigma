package com.example.technicaltestsigma.repository

import com.example.technicaltestsigma.api.ApiParam
import com.example.technicaltestsigma.api.NewsApiClient
import com.example.technicaltestsigma.model.model.sources.Source
import javax.inject.Inject

class NewsSourcesRepository @Inject constructor(
private val newsApiClient: NewsApiClient
) {

    suspend fun getSources(category : String) = newsApiClient.getSourcesByCategory(category,ApiParam.API_KEY)

    suspend fun getEverything(keyWord : String) = newsApiClient.getEverything(keyWord,ApiParam.API_KEY)

    suspend fun getArticelBySource(source : String) = newsApiClient.getArticelByCategory(source,ApiParam.API_KEY)
}