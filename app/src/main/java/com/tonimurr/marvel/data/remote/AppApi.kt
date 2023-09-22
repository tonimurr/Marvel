package com.tonimurr.marvel.data.remote

import com.tonimurr.marvel.data.model.ListBaseResponse
import com.tonimurr.marvel.data.model.ComicsDTO
import com.tonimurr.marvel.data.model.EventDTO
import com.tonimurr.marvel.data.model.MarvelCharacterDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {

    @GET("v1/public/characters")
    suspend fun getMarvelCharacters(@Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null): ListBaseResponse<MarvelCharacterDTO>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComics(@Path("characterId") id: Long, @Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null): ListBaseResponse<ComicsDTO>

    @GET("v1/public/characters/{characterId}/events")
    suspend fun getCharacterEvents(@Path("characterId") id: Long, @Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null): ListBaseResponse<EventDTO>

}