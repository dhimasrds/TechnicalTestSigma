package com.example.technicaltestsigma.model.model.article


import com.squareup.moshi.Json

data class ArticleNews(
    @Json(name = "articles")
    val articles: List<Article>? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null
)