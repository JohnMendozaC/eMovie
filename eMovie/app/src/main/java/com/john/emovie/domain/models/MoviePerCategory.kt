package com.john.emovie.domain.models

import com.john.emovie.infraestructure.network.response.MovieResponse

data class MoviePerCategory(
    val nameCategory: String,
    val listOfMovies: MovieResponse<List<Movie>>,
    val withFilter: Boolean
)