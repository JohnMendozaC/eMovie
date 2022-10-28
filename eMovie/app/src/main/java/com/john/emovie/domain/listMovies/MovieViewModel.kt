package com.john.emovie.domain.listMovies

import androidx.lifecycle.ViewModel
import com.john.emovie.domain.models.MoviePerCategory
import com.john.emovie.domain.repositories.movies.MovieRepository
import com.john.emovie.infraestructure.network.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private lateinit var moviesPerCategory: List<MoviePerCategory>
    private lateinit var movieIntent: MovieIntent

    fun onMovieIntent(movieIntent: MovieIntent) {
        this.movieIntent = movieIntent
    }

    fun loadMovies() {
        runBlocking { validateMoviesToDisplay() }
    }

    private suspend fun validateMoviesToDisplay() {
        val isActiveNetWork = movieIntent.isActiveNetwork()
        withContext(Dispatchers.IO) {
            when (val movies = movieRepository.getMoviesPerCategory(isActiveNetWork)) {
                is MovieResponse.Success -> {
                    moviesPerCategory = movies.response
                    showMovies()
                }
                is MovieResponse.Error -> {
                    movieIntent.displayNotFoundDataScreen()
                }
            }
        }
    }

    private fun showMovies() {
        movieIntent.loadListMovies(moviesPerCategory)
        movieIntent.displayMoviesScreen()
    }
}