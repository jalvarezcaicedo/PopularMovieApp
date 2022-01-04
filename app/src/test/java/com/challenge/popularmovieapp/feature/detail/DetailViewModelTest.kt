package com.challenge.popularmovieapp.feature.detail

import com.challenge.popularmovieapp.commons.BaseViewModelTest
import com.challenge.popularmovieapp.domain.model.MovieUI
import com.challenge.popularmovieapp.domain.use_case.DetailUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest : BaseViewModelTest() {

    private val detailUseCase = mock<DetailUseCase>()
    private val viewModel = DetailViewModel(detailUseCase)

    @Test
    fun `get detail movie error`() {
        runBlockingTest {
            viewModel.getMovieById(0)

            verify(detailUseCase, never()).getMovieById(any())
            assertEquals(detailUseCase.getMovieById(any()), any())
        }
    }

    @Test
    fun `get detail movie successful`() {
        runBlockingTest {
            val expected = MovieUI(
                0,
                "test",
                "/aEUYugjHTxWEPmn8zacTTwQQPT0.jpg",
                "/cinER0ESG0eJ49kXlExM0MEWGxW.jpg",
                5.5,
                "Test overview",
                "2020-10-10"
            )

            whenever(
                detailUseCase.getMovieById(any())
            ).thenReturn(expected)

            verify(detailUseCase).getMovieById(any())
            assertEquals(detailUseCase.getMovieById(0), expected)
        }
    }

}