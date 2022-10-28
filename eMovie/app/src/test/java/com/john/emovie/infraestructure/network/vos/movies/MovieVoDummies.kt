package com.john.emovie.infraestructure.network.vos.movies

object MovieVoDummies {

    val anyString = "dummyString"
    val anyInt = 0
    val anyDouble = 0.0

    fun getAnyMoviesVo() = MoviesVo(
        id = anyString,
        results = listOf(getAnyMovieVo())
    )

    fun getAnyMovieVo() = MovieVo(
        listOf(anyInt),
        anyInt,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyDouble
    )
}