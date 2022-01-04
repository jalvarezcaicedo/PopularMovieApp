package com.challenge.popularmovieapp.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.challenge.popularmovieapp.data.local.MovieDatabase
import com.challenge.popularmovieapp.data.mapper.mapToUI
import com.challenge.popularmovieapp.domain.model.MovieUI
import com.challenge.popularmovieapp.data.paging.PageKeyedRemoteMediator
import com.challenge.popularmovieapp.data.remote.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class MovieRepository(
    private val moviesDatabase: MovieDatabase,
    movieService: MovieService
) {

    val popularMoviesFlow: Flow<PagingData<MovieUI>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = PageKeyedRemoteMediator(moviesDatabase, movieService)
        ) {
            moviesDatabase.movieDao().getMoviesFlow()
        }.flow.map { it.map { entity -> entity.mapToUI() } }

    suspend fun getMovieById(movieId: Int): MovieUI? =
        moviesDatabase.movieDao().getMovieById(movieId = movieId)?.mapToUI()

    companion object {
        private const val PAGE_SIZE = 12
    }
}
