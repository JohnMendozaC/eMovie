package com.john.emovie.domain.services.online

import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.domain.models.MovieDetailDummies.getAnyMovieDetail
import com.john.emovie.domain.models.MovieDetailDummies.getAnyMovieDetailWithNotFoundGenres
import com.john.emovie.domain.models.MovieDetailDummies.getAnyMovieDetailWithNotFoundTrailer
import com.john.emovie.domain.models.MovieDummies.getAnyMovie
import com.john.emovie.domain.repositories.dblocal.MovieDetailRepositoryDbLocal
import com.john.emovie.infraestructure.network.daos.MovieDaoRetroFit
import com.john.emovie.infraestructure.network.response.MovieResponse
import com.john.emovie.infraestructure.network.vos.genres.GenresVoDummies.getAnyGenresVo
import com.john.emovie.infraestructure.network.vos.trailers.MoviesTrailerVoDummies.getAnyMoviesTrailer
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
import retrofit2.Response

class MovieDetailOnlineServiceTest {

    private lateinit var movieDetailOnlineService: MovieDetailOnlineService

    @MockK(relaxed = true)
    private lateinit var movieDaoRetroFit: MovieDaoRetroFit

    @MockK(relaxed = true)
    private lateinit var movieDetailRepositoryDbLocal: MovieDetailRepositoryDbLocal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailOnlineService =
            MovieDetailOnlineService(movieDaoRetroFit, movieDetailRepositoryDbLocal)
    }


    @Test
    fun `Given a successful get movies trailer and get movies genres of movie dao retrofit When get movies detail Then insert movie detail in database local and the response should be the movie response success`() {
        `given a successful get movies trailer and get movies genres of movie dao retrofit`()

        val movies = `when get movies detail`()

        `then insert movie detail in database local and the response should be the movie response success`(
            movies
        )
    }

    private fun `given a successful get movies trailer and get movies genres of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMoviesTrailer(any()) } returns Response.success(
            getAnyMoviesTrailer()
        )
        coEvery { movieDaoRetroFit.getMoviesGenres() } returns Response.success(
            getAnyGenresVo()
        )
    }

    private fun `when get movies detail`() = runBlocking {
        movieDetailOnlineService.getMovieDetail(getAnyMovie())
    }

    private fun `then insert movie detail in database local and the response should be the movie response success`(
        movies: MovieResponse<MovieDetail>
    ) {
        coVerify {
            movieDetailRepositoryDbLocal.insertMovieDetail(any())
        }
        withClue("the response should be the movie response success") {
            movies shouldBe MovieResponse.Success(getAnyMovieDetail())
        }
    }

    @Test
    fun `Given a error get movies trailer of movie dao retrofit When get movies detail Then insert movie detail in database local and the response should be the movie response success and the movies trailers is not found value`() {
        `given a error get movies trailer of movie dao retrofit`()

        val movies = `when get movies detail`()

        `then insert movie detail in database local and the response should be the movie response success and the movies trailers is not found value`(
            movies
        )
    }

    private fun `given a error get movies trailer of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMoviesTrailer(any()) } returns Response.error(
            404, "anyBody".toResponseBody()
        )
        coEvery { movieDaoRetroFit.getMoviesGenres() } returns Response.success(
            getAnyGenresVo()
        )
    }

    private fun `then insert movie detail in database local and the response should be the movie response success and the movies trailers is not found value`(
        movies: MovieResponse<MovieDetail>
    ) {
        coVerify {
            movieDetailRepositoryDbLocal.insertMovieDetail(any())
        }
        withClue("the response should be the movie response success") {
            movies shouldBe MovieResponse.Success(getAnyMovieDetailWithNotFoundTrailer())
        }
    }

    @Test
    fun `Given a error get movies genres of movie dao retrofit When get movies detail Then insert movie detail in database local and the response should be the movie response success and the movies genres is not found value`() {
        `given a error get movies genres of movie dao retrofit`()

        val movies = `when get movies detail`()

        `then insert movie detail in database local and the response should be the movie response success and the movies genres is not found value`(
            movies
        )
    }

    private fun `given a error get movies genres of movie dao retrofit`() {
        coEvery { movieDaoRetroFit.getMoviesTrailer(any()) } returns Response.success(
            getAnyMoviesTrailer()
        )
        coEvery { movieDaoRetroFit.getMoviesGenres() } returns Response.error(
            404, "anyBody".toResponseBody()
        )
    }

    private fun `then insert movie detail in database local and the response should be the movie response success and the movies genres is not found value`(
        movies: MovieResponse<MovieDetail>
    ) {
        coVerify {
            movieDetailRepositoryDbLocal.insertMovieDetail(any())
        }
        withClue("the response should be the movie response success") {
            movies shouldBe MovieResponse.Success(getAnyMovieDetailWithNotFoundGenres())
        }
    }

    @Test
    fun `Given any exception error When get movies detail Then the response should be the movie response error`() {
        `given any exception error`()

        val movies = `when get movies detail`()

        `then the response should be the movie response error`(
            movies
        )
    }

    private fun `given any exception error`() {
        coEvery { movieDaoRetroFit.getMoviesTrailer(any()) } throws Exception()
    }

    private fun `then the response should be the movie response error`(
        movies: MovieResponse<MovieDetail>
    ) {
        withClue("the response should be the movie response error") {
            movies shouldBe MovieResponse.Error()
        }
    }
}
