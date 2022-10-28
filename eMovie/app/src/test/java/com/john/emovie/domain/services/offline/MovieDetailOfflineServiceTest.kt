package com.john.emovie.domain.services.offline

import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.domain.models.MovieDummies.getAnyMovie
import com.john.emovie.domain.repositories.dblocal.MovieDetailRepositoryDbLocal
import com.john.emovie.infraestructure.network.response.MovieResponse
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any

class MovieDetailOfflineServiceTest {

    private lateinit var movieDetailOfflineService: MovieDetailOfflineService

    @MockK(relaxed = true)
    private lateinit var movieDetailRepositoryDbLocalMock: MovieDetailRepositoryDbLocal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailOfflineService = MovieDetailOfflineService(movieDetailRepositoryDbLocalMock)
    }

    @Test
    fun `Given a successful get of movie detail of database local When get movie detail of movie detail repository of database local Then the response movie is success`() {
        `given a successful get of movie detail of database local`()
        val response = `when get movie detail of movie detail repository of database local`()
        `then the response movie is success`(response)
    }

    private fun `given a successful get of movie detail of database local`() {
        coEvery { movieDetailRepositoryDbLocalMock.getMovieDetail(any()) } returns any()
    }

    private fun `when get movie detail of movie detail repository of database local`(): MovieResponse<MovieDetail> =
        runBlocking {
            movieDetailOfflineService.getMovieDetail(getAnyMovie())
        }

    private fun `then the response movie is success`(response: MovieResponse<MovieDetail>) {
        withClue("the response should be the movie response success") {
            response shouldBe MovieResponse.Success(any())
        }
    }

    @Test
    fun `Given a error get of movie detail of database local When get movie detail of movie detail repository of database local Then the response movie is error`() {
        `given a error get of movie detail of database local`()
        val response = `when get movie detail of movie detail repository of database local`()
        `then the response movie is error`(response)
    }

    private fun `given a error get of movie detail of database local`() {
        coEvery { movieDetailRepositoryDbLocalMock.getMovieDetail(any()) } throws Exception()
    }

    private fun `then the response movie is error`(response: MovieResponse<MovieDetail>) {
        withClue("the response should be the movie response error") {
            response shouldBe MovieResponse.Error()
        }
    }
}
