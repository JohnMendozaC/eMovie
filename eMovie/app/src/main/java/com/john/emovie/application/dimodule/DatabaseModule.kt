package com.john.emovie.application.dimodule

import android.content.Context
import com.john.emovie.infraestructure.dblocal.AppDataBase
import com.john.emovie.infraestructure.dblocal.daos.MovieDao
import com.john.emovie.infraestructure.dblocal.daos.MovieDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
            AppDataBase.getInstance(context)

    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao = appDataBase.movieDao()

    @Provides
    fun provideMovieDetailDao(appDataBase: AppDataBase): MovieDetailDao = appDataBase.movieDetailDao()
}