package com.hectorfortuna.tmdbapp.cache.repository

import com.google.common.truth.Truth
import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.hawk.ModuleHawk
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class CacheRepositoryImplTest {
    private val hawkMock = mock<ModuleHawk>()
    private val repository = CacheRepositoryImpl(hawkMock)

    @Test
    fun `test existAndIsNotNull when is success`() {
        whenever(hawkMock.contains(any())).thenReturn(true)
        whenever(hawkMock.get<PopularResponse>(any())).thenReturn(popularResponseMock())

        repository.existAndIsNotNull<PopularResponse>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isTrue()
        }
    }

    @Test
    fun `test existAndIsNotNull when contains is false`(){
        whenever(hawkMock.contains(any())).thenReturn(false)
        repository.existAndIsNotNull<PopularResponse>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isFalse()
        }
    }

    @Test
    fun `test existAndIsNotNull when get is null`(){
        whenever(hawkMock.contains(any())).thenReturn(true)
        whenever(hawkMock.get<PopularResponse>(any())).thenReturn(null)
        repository.existAndIsNotNull<PopularResponse>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isFalse()
        }
    }

    @Test
    fun `test add when is success`(){
        whenever(hawkMock.contains(any())).thenReturn(false)
        whenever(hawkMock.put<PopularResponse>(any(), any())).thenReturn(true)
        repository.add(CacheKeys.POPULAR_MOVIES, popularResponseMock()).let {
            Truth.assertThat(it).isTrue()
        }
    }

    @Test
    fun `test add when is NOT success`(){
        whenever(hawkMock.contains(any())).thenReturn(false)
        whenever(hawkMock.put<PopularResponse>(any(),any())).thenReturn(false)
        repository.add(CacheKeys.POPULAR_MOVIES, popularResponseMock()).let {
            Truth.assertThat(it).isFalse()
        }
    }

    @Test
    fun `test add when put returns an exception`(){
        whenever(hawkMock.contains(any())).thenReturn(false)
        doAnswer { throw Throwable() }.`when`(hawkMock).put<PopularResponse>(any(), any())
        repository.add(CacheKeys.POPULAR_MOVIES, popularResponseMock()).let {
            Truth.assertThat(it).isFalse()
        }
    }

    @Test
    fun `test delete when is success`(){
        whenever(hawkMock.contains(any())).thenReturn(true)
        doNothing().`when`(hawkMock).delete(any())

        repository.delete<PopularResponse>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isTrue()
        }
    }

    @Test
    fun `test delete when is NOT success`(){
        whenever(hawkMock.contains(any())).thenReturn(true)
        doAnswer { throw  Throwable() }.`when`(hawkMock).delete(any())

        repository.delete<PopularResponse>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isFalse()
        }
    }

    @Test
    fun `test get when is success`(){
        whenever(hawkMock.get<PopularResponse>(any())).thenReturn(popularResponseMock())

        repository.get<PopularResponse>(CacheKeys.POPULAR_MOVIES).let {
            Truth.assertThat(it).isEqualTo(popularResponseMock())
        }
    }

    private fun popularResponseMock() = PopularResponse(
        1, listOf(),10,10
    )
}