package com.john.emovie.domain.models

object MovieDummies {

    const val anyString = "dummyString"
    const val anyInt = 0
    const val anyDoubleString = "0.0"
    const val originalLanguageEn = "en"
    const val releaseDateNineteenHundredAndEighty = "1980"

    fun getAnyMovie() = Movie(
        listOf(anyInt),
        anyInt,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyDoubleString
    )

    fun getAnyMovieWithDifferentReleaseDate(releaseDate: String) = Movie(
        listOf(anyInt),
        anyInt,
        anyString,
        anyString,
        anyString,
        anyString,
        releaseDate,
        anyString,
        anyDoubleString
    )

    fun getAnyMovieWithDifferentOriginalLanguage(originalLanguage: String) = Movie(
        listOf(anyInt),
        anyInt,
        originalLanguage,
        anyString,
        anyString,
        anyString,
        anyString,
        anyString,
        anyDoubleString
    )

    fun getTenMovies(): List<Movie> {
        val movie = getAnyMovie()
        return listOf(movie, movie, movie, movie, movie, movie, movie, movie, movie, movie)
    }

    fun getTenMoviesWithDifferentDateAndLanguage(): List<Movie> {
        val movie = getAnyMovie()
        val movieEn = getAnyMovieWithDifferentOriginalLanguage(originalLanguageEn)
        val movieNineteenHundredAndEighty =
            getAnyMovieWithDifferentReleaseDate(releaseDateNineteenHundredAndEighty)
        return listOf(
            movie,
            movieEn,
            movieEn,
            movieEn,
            movieNineteenHundredAndEighty,
            movieNineteenHundredAndEighty,
            movieNineteenHundredAndEighty,
            movie,
            movie,
            movie
        )
    }

    fun getFiltersOfTenMoviesWithDifferentDateAndLanguage() = listOf(
        anyString.substring(0, 4),
        releaseDateNineteenHundredAndEighty,
        anyString,
        originalLanguageEn
    )

    fun getMoviesWithReleaseDateNineteenHundredAndEight(): List<Movie> {
        val movieNineteenHundredAndEighty =
            getAnyMovieWithDifferentReleaseDate(releaseDateNineteenHundredAndEighty)
        return listOf(
            movieNineteenHundredAndEighty,
            movieNineteenHundredAndEighty,
            movieNineteenHundredAndEighty
        )
    }
}
