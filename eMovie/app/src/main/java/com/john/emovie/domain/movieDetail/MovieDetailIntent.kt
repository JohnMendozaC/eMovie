package com.john.emovie.domain.movieDetail

import com.john.emovie.domain.models.MovieDetail

interface MovieDetailIntent {
    fun loadMovieDetail(movie: MovieDetail)
    fun displayMovieDetailScreen()
    fun displayMovieError()
    fun isActiveNetwork(): Boolean
}