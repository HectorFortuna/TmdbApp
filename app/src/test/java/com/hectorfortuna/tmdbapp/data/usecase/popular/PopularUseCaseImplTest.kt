package com.hectorfortuna.tmdbapp.data.usecase.popular

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.repository.popular.PopularRepositoryImpl
import com.hectorfortuna.tmdbapp.util.transformToResponseBody
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class PopularUseCaseImplTest{
    private val repository = mock<PopularRepositoryImpl>()
    private val useCase = PopularUseCaseImpl(repository)

    @Test
    fun `test getPopularMovies when is success (200)`() = runTest{
        val response = Response.success(200, popularResponseMock())
        val expected = popularResponseMock()
        whenever(repository.getPopularMovies(any(), any())).thenReturn(response)
        useCase.getPopularMovies("apikey",1).let {
            Truth.assertThat(it).isEqualTo(expected)
        }
    }

    @Test
    fun `test getPopularMovies when is null (code is not 200)`() = runTest{
        val response = Response.success(202, popularResponseMock())
        whenever(repository.getPopularMovies(any(), any())).thenReturn(response)
        useCase.getPopularMovies("apikey",1).let {
            Truth.assertThat(it).isNull()
        }
    }

    @Test
    fun `test getPopularMovies when is error(400)`() = runTest{
        val response = mockResponseError(400)
        whenever(repository.getPopularMovies(any(), any())).thenReturn(response)
        useCase.getPopularMovies("apikey",1).let {
            Truth.assertThat(it).isNull()
        }
    }

    private fun mockResponseError(code: Int) =
        Response.error<PopularResponse>(code, "".transformToResponseBody())

    private fun popularResponseMock() = PopularResponse(
        1, listOf(),1,1
    )
}