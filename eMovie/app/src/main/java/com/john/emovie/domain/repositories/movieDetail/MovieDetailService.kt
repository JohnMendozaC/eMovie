package com.john.emovie.domain.repositories.movieDetail

import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.infraestructure.network.response.MovieResponse

interface MovieDetailService {
    suspend fun getMovieDetail(movie: Movie): MovieResponse<MovieDetail>
}