package com.john.emovie.application.dimodule

import com.john.emovie.domain.repositories.dblocal.MovieDetailRepositoryDbLocal
import com.john.emovie.domain.repositories.dblocal.MovieRepositoryDbLocal
import com.john.emovie.infraestructure.dblocal.repositories.MovieDetailRepositoryRoom
import com.john.emovie.infraestructure.dblocal.repositories.MovieRepositoryRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MoviesModule {
    @Binds
    abstract fun provideMovieRepository(movieRepositoryRoom: MovieRepositoryRoom): MovieRepositoryDbLocal

    @Binds
    abstract fun provideMovieDetailRepository(movieDetailRepositoryRoom: MovieDetailRepositoryRoom): MovieDetailRepositoryDbLocal
}
