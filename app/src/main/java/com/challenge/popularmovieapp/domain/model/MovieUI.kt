package com.challenge.popularmovieapp.domain.model

import com.challenge.popularmovieapp.BuildConfig

data class MovieUI(
    val id: Int,
    val title: String,
    private val posterPath: String?,
    private val backdropPath: String?,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: String
) {
    val imagePosterUrl: String?
        get() = posterPath?.let { BuildConfig.TMDB_IMAGE_BASE_URL + posterPath }
    val imageBackdropUrl: String?
        get() = backdropPath?.let { BuildConfig.TMDB_IMAGE_BASE_URL + backdropPath }
}