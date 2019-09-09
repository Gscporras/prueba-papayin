package com.whitestudios.papayin.activities.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _navigateToMovieList = MutableLiveData<Boolean>()
    val navigateToMovieList: LiveData<Boolean>
        get() = _navigateToMovieList

    init {
        _navigateToMovieList.value = false
        GlobalScope.launch {
            delay(2000)
            _navigateToMovieList.postValue(true)
        }
    }

    fun displayMovieListComplete() {
        _navigateToMovieList.value = false
    }

    fun getAnimate(): Boolean = true


}