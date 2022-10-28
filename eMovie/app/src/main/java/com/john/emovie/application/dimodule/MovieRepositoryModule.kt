package com.john.emovie.application.dimodule

import com.john.emovie.domain.repositories.movieDetail.MovieDetailService
import com.john.emovie.domain.repositories.movies.MovieService
import com.john.emovie.domain.services.offline.MovieDetailOfflineService
import com.john.emovie.domain.services.offline.MovieOfflineService
import com.john.emovie.domain.services.online.MovieDetailOnlineService
import com.john.emovie.domain.services.online.MovieOnlineService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MovieRepositoryModule {

    @Binds
    abstract fun provideMovieOnlineService(movieOnlineService: MovieOnlineService): MovieService

    @Binds
    abstract fun provideMovieOfflineService(movieOfflineService: MovieOfflineService): MovieService

    @Binds
    abstract fun provideMovieDetailOnlineService(movieOnlineService: MovieDetailOnlineService): MovieDetailService

    @Binds
    abstract fun provideMovieDetailOfflineService(movieDetailOfflineService: MovieDetailOfflineService): MovieDetailService

}