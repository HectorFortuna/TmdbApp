package com.hectorfortuna.tmdbapp.ui.home.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.hectorfortuna.tmdbapp.R
import com.hectorfortuna.tmdbapp.core.BaseFragment
import com.hectorfortuna.tmdbapp.core.Status
import com.hectorfortuna.tmdbapp.core.ViewManager
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.FragmentHomeBinding
import com.hectorfortuna.tmdbapp.ui.adapter.home.MovieAdapter
import com.hectorfortuna.tmdbapp.ui.home.MainActivity
import com.hectorfortuna.tmdbapp.ui.home.viewmodel.HomeViewModel
import com.hectorfortuna.tmdbapp.util.CustomDialog
import com.hectorfortuna.tmdbapp.util.apiKey
import com.hectorfortuna.tmdbapp.util.hasInternet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    private val movieAdapter = MovieAdapter(::goToDetails, ::listChanged)

    private var currentPage: Int = 1
    private var resultList = mutableListOf<Result>()

    private lateinit var binding: FragmentHomeBinding

    private var apiRequest: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchRecyclerView()
        checkInternetConnection()
        getPopularMovies(currentPage)
        observeVMEvents()
        setRecyclerView()
        setupToolbar()
    }
    private fun checkInternetConnection() {
        if (ViewManager.networkFavouriteState == false) {
            setNetworkDialog()
        }
    }

    private fun goToDetails(result: Result) {
        if (ViewManager.networkFavouriteState == true) {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                Bundle().apply {
                    putInt("MOVIES", result.id)
                }
            )
        } else {
            setNetworkDialog()
        }

    }

    private fun searchMovie(query: String) {
        viewModel.searchMovie(apiKey(), query)
    }

    private fun listChanged(){
        currentPage++
        viewModel.getPopularMovies(apiKey(),currentPage)
    }

    private fun setNetworkDialog() {
        CustomDialog(
            title = "Tente Novamente",
            message = "Erro ao conectar-se a internet, por favor verifique sua conexão e tente novamente",
            textYes = "Tentar novamente",
            textNo = "Cancelar"
        ).apply {
            setListener {
                if (ViewManager.networkFavouriteState == true) {
                    getPopularMovies(currentPage)
                } else {
                    setNetworkDialog()
                }
            }
        }.show(parentFragmentManager, "Connection")
    }

    private fun getPopularMovies(page: Int) {
        viewModel.getPopularMovies(apiKey(), page)
    }

    private fun observeVMEvents() {
        viewModel.response.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    apiRequest = true
                }
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        apiRequest = false
                        resultList.addAll(response.result)
                        movieAdapter.submitList(resultList.toMutableList())
                    }
                }
                Status.ERROR -> {
                    setNetworkDialog()
                }
            }
        }

        viewModel.search.observe(viewLifecycleOwner) { state ->
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (state.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    state.data?.let {
                        movieAdapter.submitList(it.result)
                    }
                }
                Status.ERROR -> {}
            }
        }
    }

    private fun setSearchRecyclerView() {
        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun setRecyclerView() {
        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter

        }
    }

    private fun setMenu() {
        activity?.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu, menu)
                    createSearch(menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return false
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED
        )
    }

    private fun createSearch(menu: Menu) {
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.menu_search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                when (newText) {
                    "" -> getPopularMovies(1)
                    else -> searchMovie(newText.toString())
                }
                return false
            }
        })
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.includeToolbar.toolbarLayout)
        (activity as MainActivity).supportActionBar?.title = ""
        setMenu()
    }
}