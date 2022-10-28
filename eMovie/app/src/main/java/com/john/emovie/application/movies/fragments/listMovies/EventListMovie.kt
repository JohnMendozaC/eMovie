package com.john.emovie.application.movies.fragments.listMovies

import com.john.emovie.domain.models.Movie

interface EventListMovie {
    fun clickToMovie(movie: Movie)
}