package com.hectorfortuna.tmdbapp.ui.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.data.core.Status
import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.network.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.FragmentDetailsBinding
import com.hectorfortuna.tmdbapp.ui.details.viewmodel.DetailsViewModel
import com.hectorfortuna.tmdbapp.util.apiKey
import com.hectorfortuna.tmdbapp.util.imageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var results: Result

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        results = arguments?.getParcelable<Result>("MOVIES") as Result
        viewModel.getMovieDetails(apiKey(), results.id)
        observeVMEvents()
    }

    private fun observeVMEvents() {
        viewModel.response.observe(viewLifecycleOwner){
            if(viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when(it.status) {
                Status.SUCCESS ->{
                    it.data?.let {
                        initScreen(it)
                    }
                }
                Status.LOADING ->{}
                Status.ERROR ->{}
            }
        }
    }


    private fun initScreen(details: MovieDetails) {
        setDetailTexts(details)
        setImage(details)
    }

    private fun setDetailTexts(details: MovieDetails) {
        binding.apply {

        }
    }

    private fun setImage(details: MovieDetails) {
        binding.apply {
            Glide.with(this@DetailsFragment)
                .load("${imageUrl()}${details.posterPath}")
                .centerCrop()
                .into(imgPosterMovie)
        }
    }
}