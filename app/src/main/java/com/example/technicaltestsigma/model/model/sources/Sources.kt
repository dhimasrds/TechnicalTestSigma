package com.example.technicaltestsigma.model.model.sources


import com.squareup.moshi.Json

data class Sources(
    @Json(name = "sources")
    val sources: List<Source>? = null,
    @Json(name = "status")
    val status: String? = null
)