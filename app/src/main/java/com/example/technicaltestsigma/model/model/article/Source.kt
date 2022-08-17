package com.example.technicaltestsigma.model.model.article


import com.squareup.moshi.Json

data class Source(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
)