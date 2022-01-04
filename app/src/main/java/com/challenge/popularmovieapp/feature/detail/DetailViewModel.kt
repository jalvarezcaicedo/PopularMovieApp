package com.challenge.popularmovieapp.feature.detail

import androidx.lifecycle.ViewModel
import com.challenge.popularmovieapp.domain.use_case.DetailUseCase

class DetailViewModel(
    private val detailUseCase: DetailUseCase
) : ViewModel() {

    suspend fun getMovieById(movieId: Int) = detailUseCase.getMovieById(movieId)
}