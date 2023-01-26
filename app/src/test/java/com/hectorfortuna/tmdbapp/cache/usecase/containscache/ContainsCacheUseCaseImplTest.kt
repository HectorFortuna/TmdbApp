package com.hectorfortuna.tmdbapp.cache.usecase.containscache

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepositoryImpl
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.usecase.popular.PopularUseCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class ContainsCacheUseCaseImplTest{
    private val repositoryMock = mock<CacheRepositoryImpl>()
    private val useCase = ContainsCacheUseCaseImpl(repositoryMock)

    @Test
    fun `test cacheExistsAndIsNotNull is true`(){
        whenever(repositoryMock.existAndIsNotNull<PopularResponse>(any())).thenReturn(true)
        useCase.cacheExistAndIsNotNull<PopularUseCase>(CacheKeys.POPULAR_MOVIES).let {
                Truth.assertThat(it).isTrue()
        }
    }

    @Test
    fun `test cacheExistsAndIsNotNull is false`(){
        whenever(repositoryMock.existAndIsNotNull<PopularResponse>(any())).thenReturn(false)
        useCase.cacheExistAndIsNotNull<PopularUseCase>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isFalse()
        }
    }
}