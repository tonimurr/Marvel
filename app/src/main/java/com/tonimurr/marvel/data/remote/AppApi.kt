package com.tonimurr.marvel.data.remote

import com.tonimurr.marvel.BuildConfig
import com.tonimurr.marvel.data.model.BaseResponse
import com.tonimurr.marvel.data.model.MarvelCharacterDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET("v1/public/characters")
    suspend fun getMarvelCharacters(): BaseResponse<MarvelCharacterDTO>

}