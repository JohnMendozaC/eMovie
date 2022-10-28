package com.john.emovie.infraestructure.network.anticorruption

import com.john.emovie.domain.models.Movie
import com.john.emovie.infraestructure.network.vos.movies.MoviesVo

fun MoviesVo.toMovies(): List<Movie> = this.results.map {
    Movie(
        it.genreIds,
        it.id,
        it.originalLanguage,
        it.originalTitle,
        it.overview,
        it.posterPath,
        it.releaseDate,
        it.title,
        it.voteAverage.toString()
    )
}