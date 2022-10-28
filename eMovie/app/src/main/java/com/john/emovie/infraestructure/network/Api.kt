package com.john.emovie.infraestructure.network

import com.john.emovie.infraestructure.network.ParamsOfMovieApi.baseUrl
import com.john.emovie.infraestructure.network.daos.MovieDaoRetroFit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    fun create(): MovieDaoRetroFit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDaoRetroFit::class.java)
    }
}