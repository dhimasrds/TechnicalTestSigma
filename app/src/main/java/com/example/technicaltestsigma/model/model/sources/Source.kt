package com.example.technicaltestsigma.model.model.sources


import com.squareup.moshi.Json

data class Source(
    @Json(name = "category")
    val category: String? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "language")
    val language: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "url")
    val url: String? = null
)