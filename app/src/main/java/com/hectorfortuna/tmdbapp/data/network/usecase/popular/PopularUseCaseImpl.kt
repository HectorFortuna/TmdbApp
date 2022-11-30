package com.hectorfortuna.tmdbapp.data.network.usecase.popular

import com.hectorfortuna.tmdbapp.data.network.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.network.repository.popular.PopularRepository
import com.hectorfortuna.tmdbapp.data.network.usecase.popular.PopularUseCase
import javax.inject.Inject

class PopularUseCaseImpl @Inject constructor(private val repository: PopularRepository):
    PopularUseCase {
    override suspend fun getPopularMovies(apikey: String, page: Int): PopularResponse? {

        val response = repository.getPopularMovies(apikey,page)

        return if(response.isSuccessful && response.code() == 200){
            response.body()
        } else{
            null
        }
    }

}