package com.whitestudios.papayin.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.whitestudios.papayin.R
import com.whitestudios.papayin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
        setupNavigation()
    }

    private fun setupNavigation() {
        // first find the nav controller
        val navController = findNavController(R.id.nav_host_fragment)

        setSupportActionBar(binding.toolbar)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.ivToolbar.visibility = View.GONE
                    binding.tvTitleToolbar.visibility = View.VISIBLE
                    binding.tvTitleToolbar.text = getString(R.string.app_name)
                }
                R.id.movieListFragment -> {
                    binding.ivToolbar.visibility = View.VISIBLE
                    binding.tvTitleToolbar.visibility = View.GONE
                }
                else -> {
                    binding.ivToolbar.visibility = View.GONE
                    binding.tvTitleToolbar.visibility = View.VISIBLE
                    binding.tvTitleToolbar.text = getString(R.string.my_movie)

                }
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
