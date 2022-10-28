package com.john.emovie.infraestructure.dblocal.dtos

import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.infraestructure.dblocal.entitys.MovieDetailEntity

fun MovieDetail.toMovieDetailEntity(): MovieDetailEntity {
    return with(this) {
        MovieDetailEntity(
            id = id,
            genres = genres,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage,
            video = video
        )
    }
}

fun MovieDetailEntity.toDomainModel(): MovieDetail {
    return with(this) {
        MovieDetail(
            id = id,
            genres = genres,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage,
            video = video
        )
    }
}