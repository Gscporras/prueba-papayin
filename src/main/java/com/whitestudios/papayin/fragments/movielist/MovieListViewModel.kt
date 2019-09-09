package com.whitestudios.papayin.fragments.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.whitestudios.papayin.data.entity.Movie
import com.whitestudios.papayin.data.network.Api
import com.whitestudios.papayin.data.network.ApiStatus
import com.whitestudios.papayin.fragments.movielist.domain.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(Api.retrofitService)

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    init {
        getMovies()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    private fun getMovies() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val listResult = repository.getMovieListRequest(1)
                _status.value = ApiStatus.DONE
                _movies.value = listResult.movies
            } catch (e: Exception) {
                _status.value = ApiStatus.DEFAULT_ERROR
                _movies.value = emptyList()
            }
        }
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }
}