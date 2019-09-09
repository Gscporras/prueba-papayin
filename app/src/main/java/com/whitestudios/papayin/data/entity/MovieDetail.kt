package com.whitestudios.papayin.data.entity

import com.squareup.moshi.Json

data class MovieDetail(
    @Json(name = "id")
    val id: Long,
    @Json(name = "genres")
    val genres: List<Genre>,
    @Json(name = "production_companies")
    val productionCompanies: List<Company>,
    @Json(name = "production_countries")
    val productionCountries: List<Country>,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "videos")
    val videos: Video
)