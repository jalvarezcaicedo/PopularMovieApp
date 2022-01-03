package com.challenge.popularmovieapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.challenge.popularmovieapp.data.local.MovieDatabase
import com.challenge.popularmovieapp.data.mapper.mapToDomain
import com.challenge.popularmovieapp.data.model.MovieUI
import com.challenge.popularmovieapp.data.paging.PageKeyedRemoteMediator
import com.challenge.popularmovieapp.data.remote.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class MovieRepository(
    private val moviesDatabase: MovieDatabase,
    private val movieService: MovieService
) {

    val popularMoviesFlow: Flow<PagingData<MovieUI>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = PageKeyedRemoteMediator(moviesDatabase, movieService)
        ) {
            moviesDatabase.movieDao().getMoviesFlow()
        }.flow.map { it.map { entity -> entity.mapToDomain() } }

    suspend fun getMovieById(movieId: Int): MovieUI? =
        moviesDatabase.movieDao().getMovieById(movieId = movieId)?.mapToDomain()

    suspend fun deleteAllPopularMovies() {
        moviesDatabase.movieDao().deleteAllPopularMovies()
    }

    suspend fun changeNamesOfAllPopularMovies() {
        val movies = moviesDatabase.movieDao().getMovies()
        moviesDatabase.movieDao().updateMovies(movies.map { it.copy(title = it.title + " (up)") })
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
