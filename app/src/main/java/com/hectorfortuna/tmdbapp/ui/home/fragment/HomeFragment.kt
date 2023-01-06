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
import com.hectorfortuna.tmdbapp.core.Status
import com.hectorfortuna.tmdbapp.core.ViewManager
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.FragmentHomeBinding
import com.hectorfortuna.tmdbapp.ui.adapter.MovieAdapterRecyclerView
import com.hectorfortuna.tmdbapp.ui.home.MainActivity
import com.hectorfortuna.tmdbapp.ui.home.viewmodel.HomeViewModel
import com.hectorfortuna.tmdbapp.util.CustomDialog
import com.hectorfortuna.tmdbapp.util.apiKey
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var movieAdapter: MovieAdapterRecyclerView

    private var currentPage: Int = 1
    private var resultList = mutableListOf<Result>()

    private lateinit var binding: FragmentHomeBinding

    private var isSearchView: Boolean = false
    private var search: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        getPopularMovies()
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

    private fun getPopularMovies() {
        if (currentPage == 1) {
            getPopularMovies(currentPage)
        }
    }

    private fun searchMovie(query: String) {
        isSearchView
        viewModel.searchMovie(apiKey(), query)
    }

    private fun listChanged() {
        if (!isSearchView) {
            currentPage++
            viewModel.getPopularMovies(apiKey(), currentPage)
        }
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
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        if(isSearchView) resultList.clear()
                        if (response.result != resultList) resultList.addAll(response.result)
                        movieAdapter.notifyDataSetChanged()
                        isSearchView = false
                    }
                }
                Status.ERROR -> {
                    setNetworkDialog()
                }
            }
        }

        viewModel.search.observe(viewLifecycleOwner) { state ->
            when (state.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    state.data?.let {
                        isSearchView = true
                        resultList.clear()
                        resultList.addAll(it.result)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {}
            }
        }
    }

//    private fun setSearchRecyclerView(movieList: List<Result>) {
//        movieAdapter = MovieAdapterRecyclerView(movieList,::goToDetails)
//        binding.rvMovie.apply {
//            setHasFixedSize(true)
//            adapter = movieAdapter
//        }
//    }

    private fun setAdapter() {
        movieAdapter = MovieAdapterRecyclerView(resultList,::goToDetails,::listChanged)
    }

    private fun setRecyclerView() {
        setAdapter()
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
                    "" -> getPopularMovies(currentPage)
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