package com.john.emovie.domain.repositories.movies

import com.john.emovie.domain.services.offline.MovieOfflineService
import com.john.emovie.domain.services.online.MovieOnlineService
import com.john.emovie.infraestructure.network.response.MovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any

class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository

    @MockK(relaxed = true)
    private lateinit var movieOnlineService: MovieOnlineService

    @MockK(relaxed = true)
    private lateinit var movieOfflineService: MovieOfflineService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieRepository = MovieRepository(movieOnlineService, movieOfflineService)
    }

    @Test
    fun `Given a active network true When get movie per category Then movie online service get movies upcoming, top rated and recommended for you`() {
        `given a active network true`()

        `when get movie per category`()

        `then movie online service get movies upcoming, top rated and recommended for you`()
    }

    private fun `given a active network true`() {
        coEvery { movieOnlineService.getMoviesUpcoming() } returns MovieResponse.Success(any())
        coEvery { movieOnlineService.getMoviesTopRated() } returns MovieResponse.Success(any())
        coEvery { movieOnlineService.getMoviesRecommendedForYou() } returns MovieResponse.Success(
            any()
        )
    }

    private fun `when get movie per category`() = runBlocking {
        movieRepository.getMoviesPerCategory(true)
    }

    private fun `then movie online service get movies upcoming, top rated and recommended for you`() {
        coVerifySequence {
            movieOnlineService.getMoviesUpcoming()
            movieOnlineService.getMoviesTopRated()
            movieOnlineService.getMoviesRecommendedForYou()
        }
    }

    @Test
    fun `Given a active network false When get movie per category with param is active network in false Then movie offline service get movies upcoming, top rated and recommended for you`() {
        `given a active network false`()

        `when get movie per category with param is active network in false`()

        `then movie offline service get movies upcoming, top rated and recommended for you`()
    }

    private fun `given a active network false`() {
        coEvery { movieOfflineService.getMoviesUpcoming() } returns MovieResponse.Success(any())
        coEvery { movieOfflineService.getMoviesTopRated() } returns MovieResponse.Success(any())
        coEvery { movieOfflineService.getMoviesRecommendedForYou() } returns MovieResponse.Success(
            any()
        )
    }

    private fun `when get movie per category with param is active network in false`() =
        runBlocking {
            movieRepository.getMoviesPerCategory(false)
        }

    private fun `then movie offline service get movies upcoming, top rated and recommended for you`() {
        coVerifySequence {
            movieOfflineService.getMoviesUpcoming()
            movieOfflineService.getMoviesTopRated()
            movieOfflineService.getMoviesRecommendedForYou()
        }
    }

}
