package com.challenge.popularmovieapp.domain.use_case

import com.challenge.popularmovieapp.domain.repository.MovieRepository

class DetailUseCase(
    private val movieRepository: MovieRepository
) {

    suspend fun getMovieById(movieId: Int) = movieRepository.getMovieById(movieId = movieId)

}