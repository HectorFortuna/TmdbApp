package com.hectorfortuna.tmdbapp.ui.favourite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hectorfortuna.tmdbapp.R
import com.hectorfortuna.tmdbapp.core.Status
import com.hectorfortuna.tmdbapp.core.ViewManager
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.databinding.FragmentFavouriteBinding
import com.hectorfortuna.tmdbapp.ui.favourite.adapter.FavouriteAdapter
import com.hectorfortuna.tmdbapp.ui.favourite.viewmodel.FavouriteViewModel
import com.hectorfortuna.tmdbapp.util.CustomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private val viewModel by viewModels<FavouriteViewModel>()
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var movieAdapter: FavouriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVMEvents()
    }

    private fun observeVMEvents() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            when {
                it.isNotEmpty() -> {
                    setRecyclerView(it)
                }
                else -> {
                    setRecyclerView(it)
                }
            }
        }

        viewModel.delete.observe(viewLifecycleOwner) { state ->
            when (state.status) {
                Status.SUCCESS -> {
                    state.data?.let {
                        Toast.makeText(requireContext(), "Personagem Deletado", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    private fun setAdapter(result: List<MovieDetails>) {
        movieAdapter = FavouriteAdapter(result, ::goToDetail, ::deleteCharacters)
    }

    private fun goToDetail(result :MovieDetails) {
            findNavController().navigate(
                R.id.action_favouriteFragment_to_detailsFragment,
                Bundle().apply {
                    putInt("MOVIES", result.id)
                }
            )
    }

    private fun setRecyclerView(result: List<MovieDetails>) {
        setAdapter(result)
        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun deleteCharacters(result: MovieDetails) {
        CustomDialog(
            title = "Confirmação",
            message = "Tem certeza que deseja deletar esse personagem?",
            textYes = "Deletar",
            textNo = "Cancelar"
        ).apply {
            setListener {
                viewModel.deleteFavourite(result)
            }
        }.show(parentFragmentManager, "Dialog")
    }

}
