package com.whitestudios.papayin.fragments.moviedetail.domain

import com.whitestudios.papayin.data.entity.MovieDetail
import com.whitestudios.papayin.data.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getMovieDetailRequest(id: Long): MovieDetail {
        return apiService.getMovieDetailAsync(id).await()
    }

}