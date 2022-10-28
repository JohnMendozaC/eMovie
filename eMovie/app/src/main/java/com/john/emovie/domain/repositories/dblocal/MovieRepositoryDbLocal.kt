package com.john.emovie.domain.repositories.dblocal

import com.john.emovie.domain.models.Movie
import com.john.emovie.infraestructure.dblocal.entitys.MovieEntity

interface MovieRepositoryDbLocal {
    fun getAllMovies(idCategory: Int): List<Movie>
    fun insertAll(entities: List<MovieEntity>)
}