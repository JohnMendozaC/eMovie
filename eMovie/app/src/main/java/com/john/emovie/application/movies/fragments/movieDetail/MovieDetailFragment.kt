package com.john.emovie.application.movies.fragments.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.john.emovie.R
import com.john.emovie.application.movies.utilities.VerifyNetwork
import com.john.emovie.databinding.MovieDetailFragmentBinding
import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MovieDetail
import com.john.emovie.domain.movieDetail.MovieDetailIntent
import com.john.emovie.domain.movieDetail.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(), MovieDetailIntent {

    private val movieDetailViewModel : MovieDetailViewModel by viewModels()
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnMovieIntent()
        setActionToToolbar()
        setDataToMovieDetail()
    }

    private fun subscribeOnMovieIntent() {
        movieDetailViewModel.onMovieDetailIntent(this)
    }

    private fun setActionToToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_movieDetailFragment_pop)
        }
    }

    private fun setDataToMovieDetail() {
        val movie = (arguments?.get(movieKeyOfArguments) as Movie)
        movieDetailViewModel.loadMovieDetail(movie)
    }

    override fun loadMovieDetail(movie: MovieDetail) {
        binding.movieDetail = movie
    }

    override fun displayMovieDetailScreen() {
        binding.ctlMovieDetail.visibility = View.VISIBLE
        binding.notFountData.visibility = View.GONE
    }

    override fun displayMovieError() {
        binding.ctlMovieDetail.visibility = View.GONE
        binding.notFountData.visibility = View.VISIBLE
    }

    override fun isActiveNetwork() = VerifyNetwork.isActiveNetwork(requireContext())

    companion object {
        const val movieKeyOfArguments = "movie"
    }
}