package com.hectorfortuna.tmdbapp.cache.usecase.savecache

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepositoryImpl
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SaveCacheUseCaseImplTest{
    private val repositoryMock = mock<CacheRepositoryImpl>()
    private val useCase = SaveCacheUseCaseImpl(repositoryMock)

    @Test
    fun `test saveCache when is success`()= runTest{
        whenever(repositoryMock.add(CacheKeys.POPULAR_MOVIES,popularResponseMock())).thenReturn(true)
        useCase.saveCache(popularResponseMock(),CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isTrue()
        }
    }

    @Test
    fun `test saveCache when is not success`()= runTest{
        whenever(repositoryMock.add(CacheKeys.POPULAR_MOVIES,popularResponseMock())).thenReturn(false)
        useCase.saveCache(popularResponseMock(),CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isFalse()
        }
    }
    private fun popularResponseMock() = PopularResponse(
        1, listOf(),10,10
    )
}