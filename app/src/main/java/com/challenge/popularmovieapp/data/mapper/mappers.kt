package com.challenge.popularmovieapp.data.mapper

import com.challenge.popularmovieapp.data.local.entity.MovieEntity
import com.challenge.popularmovieapp.data.model.Movie
import com.challenge.popularmovieapp.data.model.MovieUI

fun MovieEntity.mapToDomain(): MovieUI = MovieUI(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview.orEmpty(),
    releaseDate = releaseDate.orEmpty()
)

fun Movie.mapToEntity(apiPageIndex: Int): MovieEntity = MovieEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    apiPageIndex = apiPageIndex
)

fun Movie.mapToDomain(): MovieUI = MovieUI(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview.orEmpty(),
    releaseDate = releaseDate.orEmpty()
)