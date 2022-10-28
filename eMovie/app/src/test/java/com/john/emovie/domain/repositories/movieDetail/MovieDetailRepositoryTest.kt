package com.john.emovie.domain.repositories.movieDetail

import com.john.emovie.domain.services.offline.MovieDetailOfflineService
import com.john.emovie.domain.services.online.MovieDetailOnlineService
import com.john.emovie.infraestructure.network.response.MovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any

class MovieDetailRepositoryTest {

    private lateinit var movieDetailRepository: MovieDetailRepository

    @MockK(relaxed = true)
    private lateinit var movieDetailOnlineService: MovieDetailOnlineService

    @MockK(relaxed = true)
    private lateinit var movieDetailOfflineService: MovieDetailOfflineService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailRepository = MovieDetailRepository(
            movieDetailOnlineService,
            movieDetailOfflineService
        )
    }

    @Test
    fun `Given a active network true When get movie detail Then movie detail online service get movie detail`() {
        `given a active network true`()

        `when get movie detail`()

        `then movie detail online service get movie detail`()
    }

    private fun `given a active network true`() {
        coEvery { movieDetailOnlineService.getMovieDetail(any()) } returns MovieResponse.Success(any())
    }

    private fun `when get movie detail`() = runBlocking {
        movieDetailRepository.getMovieDetail(any(), true)
    }

    private fun `then movie detail online service get movie detail`() {
        coVerify {
            movieDetailOnlineService.getMovieDetail(any())
        }
    }

    @Test
    fun `Given a active network false When get movie detail with param is active network in false Then movie detail offline service get movie detail`() {
        `given a active network false`()

        `when get movie detail with param is active network in false`()

        `then movie detail offline service get movie detail`()
    }

    private fun `given a active network false`() {
        coEvery { movieDetailOfflineService.getMovieDetail(any()) } returns MovieResponse.Success(any())
    }

    private fun `when get movie detail with param is active network in false`() = runBlocking {
        movieDetailRepository.getMovieDetail(any(), false)
    }

    private fun `then movie detail offline service get movie detail`() {
        coVerify {
            movieDetailOfflineService.getMovieDetail(any())
        }
    }

}
