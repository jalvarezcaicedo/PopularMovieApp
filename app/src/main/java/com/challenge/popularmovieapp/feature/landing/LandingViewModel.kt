package com.challenge.popularmovieapp.feature.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.challenge.popularmovieapp.domain.model.MovieUI
import com.challenge.popularmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class LandingViewModel(
    movieRepository: MovieRepository
) : ViewModel() {

    val popularMoviesFlow: Flow<PagingData<MovieUI>> =
        movieRepository.popularMoviesFlow.cachedIn(viewModelScope)
}