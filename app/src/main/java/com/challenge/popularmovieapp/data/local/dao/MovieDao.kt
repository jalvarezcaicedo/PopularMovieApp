package com.challenge.popularmovieapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.challenge.popularmovieapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY api_page_index")
    fun getMoviesFlow(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun deleteAllPopularMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MovieEntity>)

    @Transaction
    @Query("SELECT * FROM movies WHERE movies.id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?

    @Update
    suspend fun updateMovies(list: List<MovieEntity>)
}