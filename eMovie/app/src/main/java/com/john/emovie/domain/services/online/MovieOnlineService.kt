package com.john.emovie.domain.services.online

import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.recommendedForYouId
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.tendencyId
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.upcomingReleasesId
import com.john.emovie.domain.repositories.movies.MovieService
import com.john.emovie.domain.repositories.dblocal.MovieRepositoryDbLocal
import com.john.emovie.infraestructure.dblocal.dtos.toMovieEntity
import com.john.emovie.infraestructure.network.ParamsOfMovieApi.topRated
import com.john.emovie.infraestructure.network.ParamsOfMovieApi.upcoming
import com.john.emovie.infraestructure.network.anticorruption.toMovies
import com.john.emovie.infraestructure.network.daos.MovieDaoRetroFit
import com.john.emovie.infraestructure.network.response.MovieResponse
import javax.inject.Inject

class MovieOnlineService @Inject constructor(
    private val movieDaoRetroFit: MovieDaoRetroFit,
    private val movieRepositoryDbLocal: MovieRepositoryDbLocal
) : MovieService {

    override suspend fun getMoviesUpcoming() =
        when (val response =
            MovieResponse.validateResponse(movieDaoRetroFit.getMovies(sortBy = upcoming))) {
            is MovieResponse.Success -> {
                val movies = response.response.toMovies()
                movieRepositoryDbLocal.insertAll(movies.toMovieEntity(upcomingReleasesId))
                MovieResponse.Success(movies)
            }
            is MovieResponse.Error -> {
                MovieResponse.Error()
            }
        }

    override suspend fun getMoviesTopRated() =
        when (val response =
            MovieResponse.validateResponse(movieDaoRetroFit.getMovies(sortBy = topRated))) {
            is MovieResponse.Success -> {
                val movies = response.response.toMovies()
                movieRepositoryDbLocal.insertAll(movies.toMovieEntity(tendencyId))
                MovieResponse.Success(movies)
            }
            is MovieResponse.Error -> {
                MovieResponse.Error()
            }
        }

    override suspend fun getMoviesRecommendedForYou() =
        when (val response =
            MovieResponse.validateResponse(movieDaoRetroFit.getMoviesRecommendedForYou())) {
            is MovieResponse.Success -> {
                val movies = response.response.toMovies()
                movieRepositoryDbLocal.insertAll(movies.toMovieEntity(recommendedForYouId))
                MovieResponse.Success(movies)
            }
            is MovieResponse.Error -> {
                MovieResponse.Error()
            }
        }
}
