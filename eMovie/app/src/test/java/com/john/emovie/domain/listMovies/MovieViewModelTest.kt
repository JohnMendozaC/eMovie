package com.john.emovie.domain.listMovies

import com.john.emovie.domain.repositories.movies.MovieRepository
import com.john.emovie.infraestructure.network.response.MovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @MockK(relaxed = true)
    private lateinit var movieIntentMock: MovieIntent

    @MockK(relaxed = true)
    private lateinit var movieRepositoryMock: MovieRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieViewModel = MovieViewModel(movieRepositoryMock)
        movieViewModel.onMovieIntent(movieIntentMock)
    }

    @Test
    fun `Given a successful response When load movies Then load list movies and display movies screen`() {
        `given a successful response`()

        `when load movies`()

        `then load list movies and display movies screen`()
    }

    @Test
    fun `Given a error response When load movies Then display not found data screen`() {
        `given a error response`()

        `when load movies`()

        `then display not found data screen`()
    }

    private fun `given a successful response`() {
        coEvery { movieRepositoryMock.getMoviesPerCategory(any()) } returns MovieResponse.Success(listOf())
    }

    private fun `given a error response`() {
        coEvery { movieRepositoryMock.getMoviesPerCategory(any()) } returns MovieResponse.Error()
    }

    private fun `when load movies`() {
        movieViewModel.loadMovies()
    }

    private fun `then load list movies and display movies screen`() {
        verify {
            movieIntentMock.loadListMovies(listOf())
            movieIntentMock.displayMoviesScreen()
        }
    }

    private fun `then display not found data screen`() {
        verify {
            movieIntentMock.displayNotFoundDataScreen()
        }
    }
}
