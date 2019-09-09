package com.whitestudios.papayin.data.entity

import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String
)