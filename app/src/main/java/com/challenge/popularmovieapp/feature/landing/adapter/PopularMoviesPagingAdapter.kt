package com.challenge.popularmovieapp.feature.landing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.challenge.popularmovieapp.domain.model.MovieUI
import com.challenge.popularmovieapp.databinding.ItemMoviesBinding

class PopularMoviesPagingAdapter(
    private val onMovieClicked: (MovieUI) -> Unit
) : PagingDataAdapter<MovieUI, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(item = getItem(position), onMovieClicked = onMovieClicked)
    }
}