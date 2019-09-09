package com.whitestudios.papayin.data.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Json(name = "id")
    val id: Long,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "video")
    val video: Boolean
) : Parcelable {

    val posterUrl: String
        get() = "http://image.tmdb.org/t/p/w185$posterPath"

    val backdropUrl: String
        get() = "http://image.tmdb.org/t/p/w500$backdropPath"
}