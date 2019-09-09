package com.whitestudios.papayin.data.entity

import com.squareup.moshi.Json

data class Result(
    @Json(name = "id")
    val id: String,
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "site")
    val site: String,
    @Json(name = "type")
    val type: String
)