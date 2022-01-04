package com.challenge.popularmovieapp.feature.landing.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.popularmovieapp.databinding.ItemMoviesBinding
import com.challenge.popularmovieapp.domain.model.MovieUI

class MovieViewHolder(
    private val binding: ItemMoviesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieUI?, onMovieClicked: (MovieUI) -> Unit) {
        binding.tvMovieTitle.text = item?.title.orEmpty()
        binding.tvVoteAverage.text = item?.voteAverage.toString()
        Glide.with(binding.root)
            .load(item?.imagePosterUrl)
            .fitCenter()
            .into(binding.ivImageMovie)
        binding.root.setOnClickListener {
            item?.let {
                onMovieClicked.invoke(it)
            }
        }
    }
}