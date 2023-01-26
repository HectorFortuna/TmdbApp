package com.hectorfortuna.tmdbapp.data.usecase.details

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.data.model.moviedetails.BelongsToCollection
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.repository.MovieDetailsRepository
import com.hectorfortuna.tmdbapp.util.transformToResponseBody
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class MovieDetailsUseCaseImplTest{
    private val repository = mock<MovieDetailsRepository>()
    private val useCase = MovieDetailsUseCaseImpl(repository)

    @Test
    fun `test getMovieDetails when is success (200)`() = runTest{
        val response = Response.success(200, movieDetailsMock())
        val expected = movieDetailsMock()
        whenever(repository.getMovieDetails(any(), any())).thenReturn(response)
        useCase.getMovieDetails(1,"apikey").let {
            Truth.assertThat(it).isEqualTo(expected)
        }
    }

    @Test
    fun `test getMovieDetails when is null (code is not 200)`() = runTest{
        val response = Response.success(202, movieDetailsMock())
        whenever(repository.getMovieDetails(any(), any())).thenReturn(response)
        useCase.getMovieDetails(1,"apikey").let {
            Truth.assertThat(it).isNull()
        }
    }

    @Test
    fun `test getMovieDetails when is error(400)`() = runTest{
        val response = movieDetailsMockError(400)
        whenever(repository.getMovieDetails(any(), any())).thenReturn(response)
        useCase.getMovieDetails(1,"apikey").let {
            Truth.assertThat(it).isNull()
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
