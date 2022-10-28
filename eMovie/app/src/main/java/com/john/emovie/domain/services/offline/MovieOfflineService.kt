package com.john.emovie.domain.services.offline

import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.recommendedForYouId
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.tendencyId
import com.john.emovie.domain.repositories.movies.MovieRepository.Companion.upcomingReleasesId
import com.john.emovie.domain.repositories.movies.MovieService
import com.john.emovie.domain.repositories.dblocal.MovieRepositoryDbLocal
import com.john.emovie.infraestructure.network.response.MovieResponse
import javax.inject.Inject

class MovieOfflineService @Inject constructor(
    private val movieRepositoryDbLocal: MovieRepositoryDbLocal
) : MovieService {

    override suspend fun getMoviesUpcoming(): MovieResponse<List<Movie>> {
        val movies = movieRepositoryDbLocal.getAllMovies(upcomingReleasesId)
        if (movies.isEmpty()) {
            return MovieResponse.Error()
        }
        return MovieResponse.Success(movies)
    }

    override suspend fun getMoviesTopRated(): MovieResponse<List<Movie>> {
        val movies = movieRepositoryDbLocal.getAllMovies(tendencyId)
        if (movies.isEmpty()) {
            return MovieResponse.Error()
        }
        return MovieResponse.Success(movies)
    }

    override suspend fun getMoviesRecommendedForYou(): MovieResponse<List<Movie>> {
        val movies = movieRepositoryDbLocal.getAllMovies(recommendedForYouId)
        if (movies.isEmpty()) {
            return MovieResponse.Error()
        }
        return MovieResponse.Success(movies)
    }

}
