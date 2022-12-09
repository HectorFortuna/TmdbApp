package com.hectorfortuna.tmdbapp.ui.home.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hectorfortuna.tmdbapp.R
import com.hectorfortuna.tmdbapp.data.core.Status
import com.hectorfortuna.tmdbapp.data.network.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.FragmentHomeBinding
import com.hectorfortuna.tmdbapp.ui.adapter.home.MovieAdapter
import com.hectorfortuna.tmdbapp.ui.home.MainActivity
import com.hectorfortuna.tmdbapp.ui.home.viewmodel.HomeViewModel
import com.hectorfortuna.tmdbapp.util.apiKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    private val movieAdapter = MovieAdapter(){
        findNavController().navigate(
            R.id.action_homeFragment_to_detailsFragment,
            Bundle().apply {
                putParcelable("MOVIES", it)
            }
        )
    }

    private var currentPage: Int = 1
    private var resultList = mutableListOf<Result>()

    private lateinit var binding: FragmentHomeBinding

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
        getPopularMovies(currentPage)
        observeVMEvents()
        setRecyclerView()
        setupToolbar()
    }

    private fun searchMovie(query: String) {
        viewModel.searchMovie(apiKey(), query)
    }

    private fun getPopularMovies(page: Int) {
        viewModel.getPopularMovies(apiKey(), page)
    }

    private fun observeVMEvents() {
        viewModel.response.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (it.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        resultList.addAll(response.result)
                        movieAdapter.submitList(resultList)
                    }
                }
                Status.ERROR -> {}
            }
        }

        viewModel.search.observe(viewLifecycleOwner) { state->
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

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        recyclerView.layoutManager?.let {
                            val visibleItemCount = it.childCount
                            val totalItemCount = (layoutManager as GridLayoutManager).itemCount
                            val pastVisibleItems =
                                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                            if ((visibleItemCount + totalItemCount) >= pastVisibleItems) {
                                currentPage++
                                getPopularMovies(currentPage)
                            }

                        }
                    }
                }
            })
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