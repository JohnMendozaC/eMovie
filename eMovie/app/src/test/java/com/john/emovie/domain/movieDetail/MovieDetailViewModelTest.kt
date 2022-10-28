package com.john.emovie.domain.movieDetail

import com.john.emovie.domain.models.MovieDetailDummies.getAnyMovieDetail
import com.john.emovie.domain.models.MovieDummies.getAnyMovie
import com.john.emovie.domain.repositories.movieDetail.MovieDetailRepository
import com.john.emovie.infraestructure.network.response.MovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @MockK(relaxed = true)
    private lateinit var movieDetailRepositoryMock: MovieDetailRepository

    @MockK(relaxed = true)
    private lateinit var movieDetailIntentMock: MovieDetailIntent

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailViewModel = MovieDetailViewModel(movieDetailRepositoryMock)
        movieDetailViewModel.onMovieDetailIntent(movieDetailIntentMock)
    }

    @Test
    fun `Given a successful response When load movie detail Then load movie detail and display movie detail screen`() {
        `given a successful response`()

        `when load movie detail`()

        `then load movie detail and display movie detail screen`()
    }

    @Test
    fun `Given a error response When load movie detail Then display movie error`() {
        `given a error response`()

        `when load movie detail`()

        `then display movie error`()
    }

    private fun `given a successful response`() {
        coEvery {
            movieDetailRepositoryMock.getMovieDetail(
                any(),
                any()
            )
        } returns MovieResponse.Success(
            getAnyMovieDetail()
        )
    }

    private fun `given a error response`() {
        coEvery {
            movieDetailRepositoryMock.getMovieDetail(
                any(),
                any()
            )
        } returns MovieResponse.Error()
    }

    private fun `when load movie detail`() {
        movieDetailViewModel.loadMovieDetail(getAnyMovie())
    }

    private fun `then load movie detail and display movie detail screen`() {
        verify {
            movieDetailIntentMock.loadMovieDetail(getAnyMovieDetail())
            movieDetailIntentMock.displayMovieDetailScreen()
        }
    }

    private fun `then display movie error`() {
        verify {
            movieDetailIntentMock.displayMovieError()
        }
    }
}
