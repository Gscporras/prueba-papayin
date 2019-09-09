package com.whitestudios.papayin.activities.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.whitestudios.papayin.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSplashBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToMovieList.observe(this, Observer {
            if (it) {
                this.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMovieListFragment())
                viewModel.displayMovieListComplete()
            }
        })
        return binding.root
    }
}