package com.john.emovie.domain.repositories.movies

import com.john.emovie.domain.models.MoviePerCategory
import com.john.emovie.domain.services.offline.MovieOfflineService
import com.john.emovie.domain.services.online.MovieOnlineService
import com.john.emovie.infraestructure.network.response.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieOnlineService: MovieOnlineService,
    private val movieOfflineService: MovieOfflineService
) {

    private lateinit var movieService: MovieService

    suspend fun getMoviesPerCategory(isActiveNetWork: Boolean): MovieResponse<List<MoviePerCategory>> {
        validateStateOfNetWork(isActiveNetWork)
        return tryToGetMoviesPerCategory()
    }

    private fun validateStateOfNetWork(isActiveNetWork: Boolean) {
        movieService = if (isActiveNetWork) {
            movieOnlineService
        } else
            movieOfflineService
    }

    private suspend fun tryToGetMoviesPerCategory(): MovieResponse<List<MoviePerCategory>> {
        return try {
            val response = listOf(
                MoviePerCategory(
                    "Próximos estrenos",
                    movieService.getMoviesUpcoming(),
                    false
                ),
                MoviePerCategory(
                    "Tendencia",
                    movieService.getMoviesTopRated(),
                    false
                ),
                MoviePerCategory(
                    "Recomendados para ti",
                    movieService.getMoviesRecommendedForYou(),
                    true
                )
            )
            MovieResponse.Success(response)
        } catch (e: Exception) {
            MovieResponse.Error()
        }
    }

    companion object {
        const val upcomingReleasesId = 1
        const val tendencyId = 2
        const val recommendedForYouId = 3
    }
}