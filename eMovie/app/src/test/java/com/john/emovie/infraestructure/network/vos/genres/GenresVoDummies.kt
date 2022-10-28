package com.john.emovie.infraestructure.network.vos.genres

object GenresVoDummies {

    const val anyInt = 0
    const val anyString = "dummyString"

    fun getAnyGenresVo() = GenresVo(listOf(GenreVo(anyInt, anyString)))
}