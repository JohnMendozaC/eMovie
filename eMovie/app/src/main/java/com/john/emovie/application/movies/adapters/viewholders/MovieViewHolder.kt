package com.john.emovie.application.movies.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.john.emovie.application.movies.fragments.listMovies.EventListMovie
import com.john.emovie.databinding.ItemMovieBinding
import com.john.emovie.domain.models.Movie

class MovieViewHolder(
    private val eventListMovie: EventListMovie,
    private val itemMovieBinding: ItemMovieBinding
) : RecyclerView.ViewHolder(itemMovieBinding.root) {

    fun bind(item: Movie) {
        itemMovieBinding.movie = item.posterPath
        itemMovieBinding.setClickListener {
            eventListMovie.clickToMovie(item)
        }
        itemMovieBinding.executePendingBindings()
    }
}