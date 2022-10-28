package com.john.emovie.application.dimodule

import com.john.emovie.infraestructure.network.Api
import com.john.emovie.infraestructure.network.daos.MovieDaoRetroFit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideProductDaoRetroFit(): MovieDaoRetroFit = Api.create()
}