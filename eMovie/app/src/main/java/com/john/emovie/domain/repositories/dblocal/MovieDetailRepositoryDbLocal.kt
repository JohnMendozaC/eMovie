package com.john.emovie.domain.repositories.dblocal

import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.infraestructure.dblocal.entitys.MovieDetailEntity
import com.john.emovie.infraestructure.dblocal.entitys.MovieEntity

interface MovieDetailRepositoryDbLocal {
    fun getMovieDetail(idMovieDetail: Int): MovieDetail
    fun insertMovieDetail(entity: MovieDetailEntity)
}