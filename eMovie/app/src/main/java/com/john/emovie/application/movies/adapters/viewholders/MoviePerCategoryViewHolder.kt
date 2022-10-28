package com.john.emovie.application.movies.adapters.viewholders

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.john.emovie.R
import com.john.emovie.application.movies.adapters.MovieAdapter
import com.john.emovie.application.movies.fragments.listMovies.EventListMovie
import com.john.emovie.application.movies.fragments.movieDetail.MovieDetailFragment.Companion.movieKeyOfArguments
import com.john.emovie.databinding.ItemMoviePerCategoryBinding
import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MoviePerCategory
import com.john.emovie.infraestructure.network.response.MovieResponse

class MoviePerCategoryViewHolder(
    private val itemMoviePerCategoryBinding: ItemMoviePerCategoryBinding
) : RecyclerView.ViewHolder(itemMoviePerCategoryBinding.root) {

    fun bind(item: MoviePerCategory) {
        itemMoviePerCategoryBinding.tvTitleMovieCategory.text = item.nameCategory
        validateDataOfMovies(item.listOfMovies)
        itemMoviePerCategoryBinding.executePendingBindings()
    }

    private fun validateDataOfMovies(listOfMovies: MovieResponse<List<Movie>>) {
        when (listOfMovies) {
            is MovieResponse.Success -> {
                showListMovies(listOfMovies.response)
            }
            is MovieResponse.Error -> {
                showNotFoundMovies()
            }
        }
    }

    private fun showListMovies(listOfMovies: List<Movie>){
        with(itemMoviePerCategoryBinding) {
            tvNotFoundMovies.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
        }
        setDataToMoviesPerCategory(listOfMovies)
    }

    private fun showNotFoundMovies() {
        with(itemMoviePerCategoryBinding) {
            tvNotFoundMovies.visibility = View.VISIBLE
            rvMovies.visibility = View.GONE
        }
    }

    private fun setDataToMoviesPerCategory(movies: List<Movie>) {
        itemMoviePerCategoryBinding.rvMovies.visibility = View.VISIBLE
        itemMoviePerCategoryBinding.rvMovies.adapter =
            MovieAdapter(object : EventListMovie {
                override fun clickToMovie(movie: Movie) {
                    itemMoviePerCategoryBinding.root.findNavController().navigate(
                        R.id.action_movieFragment_to_movieDetailFragment, bundleOf(
                            movieKeyOfArguments to movie
                        )
                    )
                }
            })
        (itemMoviePerCategoryBinding.rvMovies.adapter as MovieAdapter).submitList(movies)
    }
}