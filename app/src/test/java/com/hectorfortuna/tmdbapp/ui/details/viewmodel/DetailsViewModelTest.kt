package com.hectorfortuna.tmdbapp.ui.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hectorfortuna.tmdbapp.core.State
import com.hectorfortuna.tmdbapp.data.db.usecase.DatabaseUseCaseImpl
import com.hectorfortuna.tmdbapp.data.model.moviedetails.BelongsToCollection
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.usecase.details.MovieDetailsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.BDDMockito.any
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatchers = UnconfinedTestDispatcher()
    private val moviesUseCase = mock<MovieDetailsUseCaseImpl>()
    private val dbUseCase = mock<DatabaseUseCaseImpl>()
    private val viewModel = DetailsViewModel(moviesUseCase, dbUseCase, dispatchers)
    @Mock
    private lateinit var responseObserver: Observer<State<MovieDetails>>

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMoviesDetails when is success `() = runTest {
        viewModel.response.observeForever(responseObserver)

        whenever(moviesUseCase.getMovieDetails(any(), any())).thenReturn(movieDetailsMock())

        viewModel.getMovieDetails("apikey", 1)
        verify(responseObserver).onChanged(State.success(movieDetailsMock()))
    }
    

    private fun movieDetailsMock() = MovieDetails(
        false, "backdrop",
        BelongsToCollection(1, "name", "poster", "backdrop"),
        1, listOf(), "home", 1, "id", "original",
        "original", "over", 1f, "poster", listOf(), listOf(), "1989",
        1, 2, listOf(), "released", "tagline", "title", false,
        1f, 1
    )
}