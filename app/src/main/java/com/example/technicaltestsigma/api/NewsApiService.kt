package com.example.technicaltestsigma.api

import com.example.technicaltestsigma.model.model.article.ArticleNews
import com.example.technicaltestsigma.model.model.everything.Everything
import com.example.technicaltestsigma.model.model.sources.Sources
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") keyWord: String?,
        @Query(value = "apiKey") apiKey: String?,
    ) : Response<Everything>


    @GET("v2/top-headlines/sources?")
    suspend fun getSourcesByCategory(
        @Query(value = "category") category: String?,
        @Query(value = "apiKey") apiKey: String?,
    ) : Response<Sources>


    @GET("v2/top-headlines")
    suspend fun getArticelByCategory(
        @Query(value = "sources") sources: String?,
        @Query(value = "apiKey") apiKey: String?,
    ) : Response<ArticleNews>

}