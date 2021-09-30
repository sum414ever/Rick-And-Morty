package com.simple.rickandmorty.data.net

import com.simple.rickandmorty.data.net.entity.ApiRawResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        val BASE_URL = "https://rickandmortyapi.com/"
    }

    @GET("api/character/")
    suspend fun getHeroesList(
        @Query("page") page: Int
    ): ApiRawResponse
}