package com.challenge.popularmovieapp.feature.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.challenge.popularmovieapp.R
import com.challenge.popularmovieapp.databinding.DetailFragmentBinding
import com.challenge.popularmovieapp.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val viewModel by viewModel<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) {
        lifecycleScope.launchWhenCreated {
            val movie = viewModel.getMovieById(movieId = movieId) ?: return@launchWhenCreated
            binding.titleTextView.text = movie.title
            binding.posterImageView.load(movie.imageUrl)
            binding.overviewTextView.text = movie.overview
            binding.releaseDateTextView.text = movie.releaseDate
        }
    }
}