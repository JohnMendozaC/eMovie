package com.john.emovie.infraestructure.dblocal.repositories

import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.domain.repositories.dblocal.MovieDetailRepositoryDbLocal
import com.john.emovie.infraestructure.dblocal.daos.MovieDetailDao
import com.john.emovie.infraestructure.dblocal.dtos.toDomainModel
import com.john.emovie.infraestructure.dblocal.entitys.MovieDetailEntity
import javax.inject.Inject

class MovieDetailRepositoryRoom @Inject constructor(
    private val movieDetailDao: MovieDetailDao
) : MovieDetailRepositoryDbLocal {

    override fun getMovieDetail(idMovieDetail: Int): MovieDetail {
        return movieDetailDao.getMovieDetail(idMovieDetail).toDomainModel()
    }

    override fun insertMovieDetail(entity: MovieDetailEntity) {
        return movieDetailDao.insertAll(entity)
    }
}