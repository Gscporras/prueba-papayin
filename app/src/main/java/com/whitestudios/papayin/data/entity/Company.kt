package com.whitestudios.papayin.data.entity

import com.squareup.moshi.Json

data class Company(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String
)