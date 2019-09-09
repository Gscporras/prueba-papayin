package com.whitestudios.papayin.fragments.moviedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.whitestudios.papayin.data.entity.Movie
import com.whitestudios.papayin.data.entity.MovieDetail
import com.whitestudios.papayin.data.entity.Result
import com.whitestudios.papayin.data.network.Api
import com.whitestudios.papayin.data.network.ApiStatus
import com.whitestudios.papayin.fragments.moviedetail.domain.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(Api.retrofitService)

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail

    private val _trailer = MutableLiveData<Result>()
    val trailer: LiveData<Result>
        get() = _trailer


    fun setMovie(movie: Movie) {
        _movie.value = movie
    }

    init {
        _status.value = ApiStatus.LOADING
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getMovieDetail() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val result = repository.getMovieDetailRequest(_movie.value!!.id)
                _status.value = ApiStatus.DONE
                _movieDetail.value = result
            } catch (e: Exception) {
                _status.value = ApiStatus.DEFAULT_ERROR
                _movieDetail.value = null
            }
        }
    }

    fun onPlayTrailer() {
        _trailer.value = _movieDetail.value!!.videos.trailer
    }

    fun displayTrailerComplete() {
        _trailer.value = null
    }

}