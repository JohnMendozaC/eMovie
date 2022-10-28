package com.john.emovie.application.movies.fragments.listMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.john.emovie.application.movies.adapters.MoviePerCategoryAdapter
import com.john.emovie.application.movies.utilities.VerifyNetwork
import com.john.emovie.databinding.MovieFragmentBinding
import com.john.emovie.domain.listMovies.MovieIntent
import com.john.emovie.domain.listMovies.MovieViewModel
import com.john.emovie.domain.models.MoviePerCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieIntent {

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: MovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnMovieIntent()
        subscribeAdapterListMovie()
        movieViewModel.loadMovies()
    }

    private fun subscribeOnMovieIntent() {
        movieViewModel.onMovieIntent(this)
    }

    private fun subscribeAdapterListMovie() {
        binding.rvMoviesPerCategory.adapter = MoviePerCategoryAdapter()
    }

    override fun loadListMovies(movies: List<MoviePerCategory>) {
        (binding.rvMoviesPerCategory.adapter as MoviePerCategoryAdapter).submitList(movies)
    }

    override fun displayMoviesScreen() {
        binding.rvMoviesPerCategory.visibility = View.VISIBLE
        binding.notFountData.visibility = View.GONE
    }

    override fun displayNotFoundDataScreen() {
        binding.rvMoviesPerCategory.visibility = View.VISIBLE
        binding.notFountData.visibility = View.GONE
    }

    override fun isActiveNetwork() = VerifyNetwork.isActiveNetwork(requireContext())
}