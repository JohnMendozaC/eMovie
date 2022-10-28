package com.john.emovie.domain.services.online

import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MovieDummies.getAnyMovie
import com.john.emovie.domain.repositories.dblocal.MovieRepositoryDbLocal
import com.john.emovie.infraestructure.network.daos.MovieDaoRetroFit
import com.john.emovie.infraestructure.network.response.MovieResponse
import com.john.emovie.infraestructure.network.vos.movies.MovieVoDummies.getAnyMoviesVo
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import retrofit2.Response

class MovieOnlineServiceTest {

    private lateinit var movieOnlineService: MovieOnlineService

    @MockK(relaxed = true)
    private lateinit var movieDaoRetroFit: MovieDaoRetroFit

    @MockK(relaxed = true)
    private lateinit var movieRepositoryDbLocal: MovieRepositoryDbLocal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieOnlineService = MovieOnlineService(movieDaoRetroFit, movieRepositoryDbLocal)
    }

    @Test
    fun `Given a successful get movies of movie dao retrofit When get movies upcoming Then insert all movie in database local and the response should be the movie response success`() {
        `given a successful get movies of movie dao retrofit`()

        val movies = `when get movies upcoming`()

        `then insert all movie in database local and the response should be the movie response success`(movies)
    }

    private fun `when get movies upcoming`() = runBlocking {
        movieOnlineService.getMoviesUpcoming()
    }

    @Test
    fun `Given a error get movies of movie dao retrofit When get movies upcoming Then the response should be the movie response error`() {
        `given a error get movies of movie dao retrofit`()

        val movies = `when get movies upcoming`()

        `then the response should be the movie response error`(movies)
    }

    @Test
    fun `Given a successful get movies of movie dao retrofit When get movies top rated Then movie detail online service get movie detail`() {
        `given a successful get movies of movie dao retrofit`()

        val movies = `when get movies top rated`()

        `then insert all movie in database local and the response should be the movie response success`(movies)
    }

    private fun `when get movies top rated`() = runBlocking {
        movieOnlineService.getMoviesTopRated()
    }

    @Test
    fun `Given a error get movies of movie dao retrofit When get movies top rated Then the response should be the movie response error`() {
        `given a error get movies of movie dao retrofit`()

        val movies = `when get movies top rated`()

        `then the response should be the movie response error`(movies)
    }

    @Test
    fun `Given a successful get movies recommended for you of movie dao retrofit When get movies recommended for you Then movie detail online service get movie detail`() {
        `given a successful get movies recommended for you of movie dao retrofit`()

        val movies = `when get movies recommended for you`()

        `then insert all movie in database local and the response should be the movie response success`(movies)
    }

    private fun `when get movies recommended for you`() = runBlocking {
        movieOnlineService.getMoviesRecommendedForYou()
    }

    @Test
    fun `Given a error get movies of movie dao retrofit When get movies recommended for you Then the response should be the movie response error`() {
        `given a error get movies recommended for you of movie dao retrofit`()

        val movies = `when get movies recommended for you`()

        `then the response should be the movie response error`(movies)
    }

    private fun `given a successful get movies recommended for you of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMoviesRecommendedForYou() } returns Response.success(
            getAnyMoviesVo()
        )
    }
    private fun `given a error get movies recommended for you of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMoviesRecommendedForYou() } returns Response.error(
                404, "anyBody".toResponseBody(any())
        )
    }

    private fun `given a successful get movies of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMovies(sortBy = any()) } returns Response.success(
            getAnyMoviesVo()
        )
    }

    private fun `given a error get movies of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMovies(sortBy = any()) } returns Response.error(
            404, "anyBody".toResponseBody(any())
        )
    }

    private fun `then insert all movie in database local and the response should be the movie response success`(movies: MovieResponse<List<Movie>>) {
        coVerify {
            movieRepositoryDbLocal.insertAll(any())
        }
        withClue("the response should be the movie response success") {
            movies shouldBe MovieResponse.Success(listOf(getAnyMovie()))
        }
    }

    private fun `then the response should be the movie response error`(movies: MovieResponse<List<Movie>>) {
        withClue("the response should be the movie response error") {
            movies shouldBe MovieResponse.Error()
        }
    }
}