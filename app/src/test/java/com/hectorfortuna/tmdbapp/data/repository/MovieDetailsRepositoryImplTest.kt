package com.hectorfortuna.tmdbapp.data.repository

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.data.model.moviedetails.BelongsToCollection
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.network.api.Service
import com.hectorfortuna.tmdbapp.util.transformToResponseBody
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class MovieDetailsRepositoryImplTest {
    private val apiMock = mock<Service>()
    private val repository = MovieDetailsRepositoryImpl(apiMock)

    @Test
    fun `test getMovieDetails when is success`() = runTest{
        val response = Response.success(movieDetailsMock())
        whenever(apiMock.getMovieDetails(any(), any())).thenReturn(response)
        repository.getMovieDetails(2,"apikey").let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }

    @Test
    fun `test getMovieDetails when api response is 4xx`() = runTest{
        val response = movieDetailsMockError(400)
        whenever(apiMock.getMovieDetails(any(), any())).thenReturn(response)
        repository.getMovieDetails(2,"apikey").let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }

    private fun movieDetailsMock() = MovieDetails(
        false, "backdrop",
        BelongsToCollection(1, "name", "poster", "backdrop"),
        1, listOf(), "home", 1, "id", "original",
        "original", "over", 1f, "poster", listOf(), listOf(), "1989",
        1, 2, listOf(), "released", "tagline", "title", false,
        1f, 1
    )
    private fun movieDetailsMockError(code: Int) =
        Response.error<MovieDetails>(code, "".transformToResponseBody())
}