package com.challenge.popularmovieapp.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.popularmovieapp.domain.model.MovieUI
import com.challenge.popularmovieapp.domain.repository.MovieRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailUseCaseTest : TestCase() {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private val detailUseCase by lazy { DetailUseCase(movieRepository) }

    @Test
    suspend fun testDetailUseCase_getDetailMovie_Completed() {
        whenever(movieRepository.getMovieById(0)).thenReturn(
            MovieUI(
                id = 0,
                title = "test",
                posterPath = "/aEUYugjHTxWEPmn8zacTTwQQPT0.jpg",
                backdropPath = "/cinER0ESG0eJ49kXlExM0MEWGxW.jpg",
                voteAverage = 5.5,
                overview = "Test overview",
                releaseDate = "2021-09-01"
            )
        )

        verify(detailUseCase).getMovieById(any())
    }

}