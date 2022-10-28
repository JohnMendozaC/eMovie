package com.john.emovie.domain.services.offline

import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MovieDummies.getAnyMovie
import com.john.emovie.domain.repositories.dblocal.MovieRepositoryDbLocal
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.recommendedForYouId
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.tendencyId
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.upcomingReleasesId
import com.john.emovie.infraestructure.network.response.MovieResponse
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieOfflineServiceTest {

    private lateinit var movieOfflineService: MovieOfflineService

    @MockK(relaxed = true)
    private lateinit var movieRepositoryDbLocalMock: MovieRepositoryDbLocal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieOfflineService = MovieOfflineService(movieRepositoryDbLocalMock)
    }

    @Test
    fun `Given a successful get all movies upcoming releases of database local When get movies upcoming repository of database local Then the response movies is success`() {
        `given a successful get all movies upcoming releases of database local`()
        val response = `when get movies upcoming repository of database local`()
        `then the response movies is success`(response)
    }

    @Test
    fun `Given a error get all movies upcoming releases of database local When get movies upcoming repository of database local Then the response movies is error`() {
        `given a error get all movies upcoming releases of database local`()
        val response = `when get movies upcoming repository of database local`()
        `then the response movies is error`(response)
    }

    private fun `given a successful get all movies upcoming releases of database local`() {
        coEvery { movieRepositoryDbLocalMock.getAllMovies(upcomingReleasesId) } returns listOf(
            getAnyMovie()
        )
    }

    private fun `when get movies upcoming repository of database local`(): MovieResponse<List<Movie>> =
        runBlocking {
            movieOfflineService.getMoviesUpcoming()
        }

    private fun `given a error get all movies upcoming releases of database local`() {
        coEvery { movieRepositoryDbLocalMock.getAllMovies(upcomingReleasesId) } returns listOf()
    }

    @Test
    fun `Given a successful get all movies top rated of database local When get movies upcoming repository of database local Then the response movies is success`() {
        `given a successful get all movies top rated of database local`()
        val response = `when get movies top rated repository of database local`()
        `then the response movies is success`(response)
    }

    @Test
    fun `Given a error get all movies top rated of database local When get movies top rated repository of database local Then the response movies is error`() {
        `given a error get all movies top rated of database local`()
        val response = `when get movies upcoming repository of database local`()
        `then the response movies is error`(response)
    }

    private fun `given a successful get all movies top rated of database local`() {
        coEvery { movieRepositoryDbLocalMock.getAllMovies(tendencyId) } returns listOf(
            getAnyMovie()
        )
    }

    private fun `when get movies top rated repository of database local`(): MovieResponse<List<Movie>> =
        runBlocking {
            movieOfflineService.getMoviesTopRated()
        }

    private fun `given a error get all movies top rated of database local`() {
        coEvery { movieRepositoryDbLocalMock.getAllMovies(upcomingReleasesId) } returns listOf()
    }

    @Test
    fun `Given a successful get all movies recommended for you of database local When get movies recommended for you repository of database local Then the response movies is success`() {
        `given a successful get all movies recommended for you of database local`()
        val response = `when get movies recommended for you repository of database local`()
        `then the response movies is success`(response)
    }

    @Test
    fun `Given a error get all movies recommended for you of database local When get movies recommended for you repository of database local Then the response movies is error`() {
        `given a error get all movies recommended for you of database local`()
        val response = `when get movies recommended for you repository of database local`()
        `then the response movies is error`(response)
    }

    private fun `given a successful get all movies recommended for you of database local`() {
        coEvery { movieRepositoryDbLocalMock.getAllMovies(recommendedForYouId) } returns listOf(
            getAnyMovie()
        )
    }

    private fun `when get movies recommended for you repository of database local`(): MovieResponse<List<Movie>> =
        runBlocking {
            movieOfflineService.getMoviesRecommendedForYou()
        }

    private fun `given a error get all movies recommended for you of database local`() {
        coEvery { movieRepositoryDbLocalMock.getAllMovies(upcomingReleasesId) } returns listOf()
    }

    private fun `then the response movies is success`(response: MovieResponse<List<Movie>>) {
        withClue("the response should be the movies response success") {
            response shouldBe MovieResponse.Success(listOf(getAnyMovie()))
        }
    }

    private fun `then the response movies is error`(response: MovieResponse<List<Movie>>) {
        withClue("the response should be the movie response error") {
            response shouldBe MovieResponse.Error()
        }
    }
}
