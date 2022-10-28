package com.john.emovie.infraestructure.dblocal.repositories

import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.repositories.dblocal.MovieRepositoryDbLocal
import com.john.emovie.infraestructure.dblocal.daos.MovieDao
import com.john.emovie.infraestructure.dblocal.dtos.toDomainModel
import com.john.emovie.infraestructure.dblocal.entitys.MovieEntity
import javax.inject.Inject

class MovieRepositoryRoom @Inject constructor(
    private val movieDao: MovieDao
) : MovieRepositoryDbLocal {

    override fun getAllMovies(idCategory: Int): List<Movie> {
        return movieDao.getAllMovies(idCategory).toDomainModel()
    }

    override fun insertAll(entities: List<MovieEntity>) {
        return movieDao.insertAll(entities)
    }
}