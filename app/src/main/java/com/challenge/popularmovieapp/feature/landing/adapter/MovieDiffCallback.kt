package com.challenge.popularmovieapp.feature.landing.adapter

import androidx.recyclerview.widget.DiffUtil
import com.challenge.popularmovieapp.domain.model.MovieUI

class MovieDiffCallback : DiffUtil.ItemCallback<MovieUI>() {

    override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
        return oldItem == newItem
    }
}