package com.hectorfortuna.tmdbapp.ui.home.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hectorfortuna.tmdbapp.data.core.Status
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.FragmentHomeBinding
import com.hectorfortuna.tmdbapp.ui.adapter.home.MovieAdapter
import com.hectorfortuna.tmdbapp.ui.home.viewmodel.HomeViewModel
import com.hectorfortuna.tmdbapp.util.apiKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var movieAdapter: MovieAdapter

    private var currentPage: Int = 1
    private var resultList = mutableListOf<com.hectorfortuna.tmdbapp.data.model.popular.Result>()

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
        getPopularMovies(currentPage)
        observeVMEvents()
        setRecyclerView()

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
                        movieAdapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {}
            }
        }
    }

    private fun setAdapter() {
        movieAdapter = MovieAdapter(resultList)
    }

    private fun setRecyclerView() {
        setAdapter()
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

}