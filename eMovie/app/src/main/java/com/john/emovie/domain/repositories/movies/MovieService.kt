package com.john.emovie.domain.repositories.movies

import com.john.emovie.domain.models.Movie
import com.john.emovie.infraestructure.network.response.MovieResponse

interface MovieService {
    suspend fun getMoviesUpcoming(): MovieResponse<List<Movie>>
    suspend fun getMoviesTopRated(): MovieResponse<List<Movie>>
    suspend fun getMoviesRecommendedForYou(): MovieResponse<List<Movie>>
}