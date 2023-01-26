package com.hectorfortuna.tmdbapp.cache.usecase.getcache

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepositoryImpl
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCacheUseCaseImplTest{
    private val repositoryMock = mock<CacheRepositoryImpl>()
    private val useCase = GetCacheUseCaseImpl(repositoryMock)

    @Test
    fun`test getPopular when is success`() = runTest{
        whenever(repositoryMock.get<PopularResponse>(CacheKeys.POPULAR_MOVIES)).thenReturn(popularResponseMock())
        useCase.getPopular(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isEqualTo(popularResponseMock())
        }
    }

    @Test
    fun`test getPopular when is null`() = runTest{
        whenever(repositoryMock.get<PopularResponse>(CacheKeys.POPULAR_MOVIES)).thenReturn(null)
        useCase.getPopular(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isNull()
        }
    }

    private fun popularResponseMock() = PopularResponse(
        1, listOf(),10,10
    )
}