package com.tonimurr.marvel.domain.repositories

import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getMarvelCharacters(offset: Int? = null, limit: Int? = null): Flow<ListDataResponse<MarvelCharacter>>

}