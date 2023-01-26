package com.hectorfortuna.tmdbapp.data.repository.popular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.network.api.Service
import com.hectorfortuna.tmdbapp.util.transformToResponseBody
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularRepositoryImplTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val serviceMock = mock<Service>()
    private val repository = PopularRepositoryImpl(serviceMock)

    @Test
    fun `test getPopularMovies when is success`() = runTest {
        val response = Response.success(popularResponseMock())
        whenever(serviceMock.getPopularMovies(any(), any())).thenReturn(response)
        repository.getPopularMovies("apikey", 1).let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }

    @Test
    fun `test getPopularMovies when response is 4xx `() = runTest {
        val response = mockResponseError(400)
        whenever(serviceMock.getPopularMovies(any(),any())).thenReturn(response)
        repository.getPopularMovies("apikey",1).let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }

    @Test
    fun `test searchMovie when is success `() = runTest {
        val response = Response.success(popularResponseMock())
        whenever(serviceMock.searchMovie(any(),any())).thenReturn(response)
        repository.searchMovie("apikey", "query").let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }
    @Test
    fun `test searchMovie when response is 4xx `() = runTest {
        val response = mockResponseError(400)
        whenever(serviceMock.searchMovie(any(),any())).thenReturn(response)
        repository.searchMovie("apikey","query").let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }

    private fun mockResponseError(code: Int) =
        Response.error<PopularResponse>(code, "".transformToResponseBody())

    private fun popularResponseMock() = PopularResponse(
        1, listOf(),1,1
    )

}