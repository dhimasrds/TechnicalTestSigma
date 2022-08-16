package com.example.technicaltestsigma.model.model.everything


import com.squareup.moshi.Json

data class Everything(
    @Json(name = "articles")
    val articles: List<Article?>? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null
)