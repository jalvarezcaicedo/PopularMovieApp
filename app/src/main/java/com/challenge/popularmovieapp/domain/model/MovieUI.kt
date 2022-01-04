package com.challenge.popularmovieapp.domain.model

import com.challenge.popularmovieapp.BuildConfig

data class MovieUI(
    val id: Int,
    val title: String,
    private val posterPath: String?,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: String
) {
    val imageUrl: String?
        get() = posterPath?.let { BuildConfig.TMDB_IMAGE_BASE_URL + posterPath }
}