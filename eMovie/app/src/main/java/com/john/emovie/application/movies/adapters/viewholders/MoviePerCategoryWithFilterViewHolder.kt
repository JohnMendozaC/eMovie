package com.john.emovie.application.movies.adapters.viewholders

import android.content.Context
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.john.emovie.R
import com.john.emovie.application.movies.adapters.MovieAdapter
import com.john.emovie.application.movies.fragments.listMovies.EventListMovie
import com.john.emovie.application.movies.fragments.movieDetail.MovieDetailFragment.Companion.movieKeyOfArguments
import com.john.emovie.databinding.ItemMoviePerCategoryWithFilterBinding
import com.john.emovie.domain.entities.MovieFilter
import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MoviePerCategory
import com.john.emovie.infraestructure.network.response.MovieResponse


class MoviePerCategoryWithFilterViewHolder(
    private val itemMoviePerCategoryWithFilterBinding: ItemMoviePerCategoryWithFilterBinding
) : RecyclerView.ViewHolder(itemMoviePerCategoryWithFilterBinding.root) {

    private lateinit var movieFilter: MovieFilter

    fun bind(item: MoviePerCategory) {
        itemMoviePerCategoryWithFilterBinding.tvTitleMovieCategoryWithFilter.text =
            item.nameCategory
        validateDataOfMovies(item.listOfMovies)
        itemMoviePerCategoryWithFilterBinding.executePendingBindings()
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
        with(itemMoviePerCategoryWithFilterBinding) {
            tvNotFoundMovies.visibility = View.GONE
            cgMovieWithFilter.visibility = View.VISIBLE
            rvMoviesWithFilter.visibility = View.VISIBLE
        }
        setDataToChipFilterMoviesPerCategory(listOfMovies)
        setDataToMoviesPerCategory()
    }

    private fun showNotFoundMovies() {
        with(itemMoviePerCategoryWithFilterBinding) {
            tvNotFoundMovies.visibility = View.VISIBLE
            cgMovieWithFilter.visibility = View.GONE
            rvMoviesWithFilter.visibility = View.GONE
        }
    }

    private fun setDataToChipFilterMoviesPerCategory(listOfMovies: List<Movie>) {
        movieFilter = MovieFilter(listOfMovies)
        with(itemMoviePerCategoryWithFilterBinding) {
            movieFilter.getFilters().forEach { title ->
                val chip = createTagChip(root.context, title)
                cgMovieWithFilter.addView(chip)
            }
            cgMovieWithFilter.visibility = View.VISIBLE
        }
    }

    private fun createTagChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            setTextAppearance(R.style.ChipTextAppearance)
            isCheckable = true
            text = chipName
            setOnCheckedChangeListener { _, _ ->
                submitListOfMovies(movieFilter.obtainFilteredMovies(chipName))
            }
        }
    }

    private fun setDataToMoviesPerCategory() {
        with(itemMoviePerCategoryWithFilterBinding.rvMoviesWithFilter){
            adapter =
            MovieAdapter(object : EventListMovie {
                override fun clickToMovie(movie: Movie) {
                    itemMoviePerCategoryWithFilterBinding.root.findNavController().navigate(
                        R.id.action_movieFragment_to_movieDetailFragment, bundleOf(
                            movieKeyOfArguments to movie
                        )
                    )
                }
            })
            submitListOfMovies(movieFilter.getDefaultMovies())
            visibility = View.VISIBLE
        }
    }

    private fun submitListOfMovies(movies: List<Movie>) {
        (itemMoviePerCategoryWithFilterBinding.rvMoviesWithFilter.adapter as MovieAdapter).submitList(
            movies
        )
    }
}
