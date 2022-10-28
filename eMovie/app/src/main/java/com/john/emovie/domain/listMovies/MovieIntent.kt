package com.john.emovie.domain.listMovies

import com.john.emovie.domain.models.MoviePerCategory

interface MovieIntent {
    fun loadListMovies(movies: List<MoviePerCategory>)
    fun displayMoviesScreen()
    fun displayNotFoundDataScreen()
    fun isActiveNetwork(): Boolean
}