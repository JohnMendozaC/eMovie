package com.john.emovie.infraestructure.network.vos.trailers

object MoviesTrailerVoDummies {

    const val anyString = "dummyString"

    fun getAnyMoviesTrailer() = MoviesTrailerVo(
        anyString,
        listOf(MovieTrailerVo(anyString, anyString))
    )
}