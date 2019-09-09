package com.whitestudios.papayin.fragments.movielist.domain

import com.whitestudios.papayin.data.entity.MovieList
import com.whitestudios.papayin.data.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getMovieListRequest(page: Int): MovieList {
        return apiService.getMovieListAsync(page).await()
    }

}