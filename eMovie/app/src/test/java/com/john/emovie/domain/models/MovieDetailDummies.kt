package com.john.emovie.domain.models

import com.john.emovie.domain.services.online.MovieDetailOnlineService.Companion.notFound


object MovieDetailDummies {

    const val anyString = "dummyString"
    const val anyInt = 0
    const val anyVideo = "https://www.youtube.com/watch?v=dummyString"
    const val anyDoubleString = "0.0"

    fun getAnyMovieDetail() = MovieDetail(
        anyString,
        anyInt,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyVideo,
        anyDoubleString
    )

    fun getAnyMovieDetailWithNotFoundTrailer() = MovieDetail(
        anyString,
        anyInt,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        notFound,
        anyDoubleString
    )

    fun getAnyMovieDetailWithNotFoundGenres() = MovieDetail(
        notFound,
        anyInt,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyVideo,
        anyDoubleString
    )
}