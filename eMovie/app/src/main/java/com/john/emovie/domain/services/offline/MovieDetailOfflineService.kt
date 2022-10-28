package com.john.emovie.domain.services.offline

import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.domain.repositories.dblocal.MovieDetailRepositoryDbLocal
import com.john.emovie.domain.repositories.movieDetail.MovieDetailService
import com.john.emovie.infraestructure.network.response.MovieResponse
import javax.inject.Inject

class MovieDetailOfflineService @Inject constructor(
    private val movieDetailRepositoryDbLocal: MovieDetailRepositoryDbLocal
) : MovieDetailService {

    override suspend fun getMovieDetail(movie: Movie): MovieResponse<MovieDetail> {
        return try {
            val movieDetail = movieDetailRepositoryDbLocal.getMovieDetail(movie.id)
            MovieResponse.Success(movieDetail)
        } catch (e: Exception) {
            MovieResponse.Error()
        }
    }
}