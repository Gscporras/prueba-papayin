package com.whitestudios.papayin.fragments.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.whitestudios.papayin.R
import com.whitestudios.papayin.activities.trailer.TrailerActivity
import com.whitestudios.papayin.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel.setMovie(MovieDetailFragmentArgs.fromBundle(arguments!!).movieSelected)
        viewModel.getMovieDetail()
        binding.viewModel = viewModel
        viewModel.movieDetail.observe(this, Observer {
            if (null != it) {

                val genres = it.genres.map { genre -> genre.name }
                binding.tvGender.text = String.format(getString(R.string.genre_format), genres.joinToString(", "))

                binding.tvDate.text = String.format(getString(R.string.date_format), it.releaseDate)

                val countries = it.productionCountries.map { country -> country.name }
                binding.tvCountry.text = String.format(getString(R.string.country_format), countries.joinToString(", "))

                val companies = it.productionCompanies.map { company -> company.name }
                binding.tvCompanies.text =
                    String.format(getString(R.string.company_format), companies.joinToString(", "))
            }
        })

        viewModel.trailer.observe(this, Observer {
            if (null != it) {
                startActivity(TrailerActivity.newIntent(requireActivity(), it.key))
                viewModel.displayTrailerComplete()
            }
        })
        return binding.root
    }
}