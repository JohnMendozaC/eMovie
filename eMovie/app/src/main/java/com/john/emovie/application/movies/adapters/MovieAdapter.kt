package com.john.emovie.application.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.john.emovie.application.movies.adapters.viewholders.MovieViewHolder
import com.john.emovie.application.movies.fragments.listMovies.EventListMovie
import com.john.emovie.databinding.ItemMovieBinding
import com.john.emovie.domain.models.Movie

class MovieAdapter(private val eventListMovie: EventListMovie) :
    ListAdapter<Movie, MovieViewHolder>(
        MovieDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            eventListMovie,
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}
