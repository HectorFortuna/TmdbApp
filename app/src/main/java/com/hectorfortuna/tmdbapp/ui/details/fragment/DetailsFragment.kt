package com.hectorfortuna.tmdbapp.ui.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.R
import com.hectorfortuna.tmdbapp.core.Status
import com.hectorfortuna.tmdbapp.core.ViewManager
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.databinding.FragmentDetailsBinding
import com.hectorfortuna.tmdbapp.ui.details.viewmodel.DetailsViewModel
import com.hectorfortuna.tmdbapp.util.DetailsDialog
import com.hectorfortuna.tmdbapp.util.apiKey
import com.hectorfortuna.tmdbapp.util.imageUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var results: Int = 0
    private lateinit var binding: FragmentDetailsBinding

    private val viewModel by viewModels<DetailsViewModel>()
    private var checkCharacter: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        results = arguments?.getInt("MOVIES") ?: 0
        if(ViewManager.networkFavouriteState == true) {
            viewModel.getMovieDetails(apiKey(), results)
        } else{
            setDialog()
        }
        observeVMEvents()
    }

    private fun setDialog(){
        DetailsDialog().apply {
            setListener {
                findNavController().navigate(
                    R.id.homeFragment
                )
            }
        }.show(parentFragmentManager, "Dialog")
    }

    private fun setFavouriteMovies(favourites: MovieDetails) {
        with(binding) {
            favouriteButton.setOnClickListener {
                checkCharacter = if (checkCharacter) {
                    viewModel.deleteFavourite(favourites)
                    favouriteButton.text = "FAVORITAR"
                    false

                } else {
                    viewModel.insertFavourite(favourites)
                    favouriteButton.text = "FAVORITADO"
                    true
                }
            }
        }
    }

    private fun observeVMEvents() {
        viewModel.response.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { details ->
                        initScreen(details)
                    }
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
        viewModel.verifySavedMovie.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { exist ->
                        if (exist) {
                            binding.favouriteButton.text = "FAVORITADO"
                        } else{
                            binding.favouriteButton.text = "FAVORITAR"
                        }
                        checkCharacter = exist
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    private fun initScreen(details: MovieDetails) {
        viewModel.verifySavedMovie(details.id)
        setDetailTexts(details)
        setImage(details)
        setFavouriteMovies(details)
    }


    private fun setDetailTexts(details: MovieDetails) {
        binding.apply {
            originalTitle.text = details.originalTitle
            txtDescription.text = details.overView
            genres.text = details.genres[0].name
            releaseDate.text = details.releaseDate
            status.text = details.status
            spokenLanguage.text = details.spokenLanguages[0].name
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