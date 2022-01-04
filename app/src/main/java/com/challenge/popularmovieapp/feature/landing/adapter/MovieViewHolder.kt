package com.challenge.popularmovieapp.feature.landing.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.challenge.popularmovieapp.domain.model.MovieUI
import com.challenge.popularmovieapp.databinding.ItemMoviesBinding

class MovieViewHolder(
    private val binding: ItemMoviesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieUI?, onMovieClicked: (MovieUI) -> Unit) {
        binding.tvMovieTitle.text = item?.title.orEmpty()
        binding.tvVoteAverage.text = item?.voteAverage.toString()
        binding.ivImageMovie.load(item?.imageUrl)
        binding.root.setOnClickListener {
            item?.let {
                onMovieClicked.invoke(it)
            }
        }
    }
}