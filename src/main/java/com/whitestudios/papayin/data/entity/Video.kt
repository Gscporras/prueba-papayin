package com.whitestudios.papayin.data.entity

import com.squareup.moshi.Json

data class Video(
    @Json(name = "results")
    val results: List<Result>
) {
    val trailer: Result?
        get() {
            return results.firstOrNull {
                it.type == "Trailer" && it.site == "YouTube"
            }
        }
}