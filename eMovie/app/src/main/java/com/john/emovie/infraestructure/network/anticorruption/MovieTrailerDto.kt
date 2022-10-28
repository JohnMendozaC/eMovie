package com.john.emovie.infraestructure.network.anticorruption

import com.john.emovie.infraestructure.network.vos.trailers.MoviesTrailerVo

fun MoviesTrailerVo.toLinkMovieTrailer(positionTrailer: Int = 0): String =
    youtubeLink + this.results[positionTrailer].key

const val youtubeLink = "https://www.youtube.com/watch?v="