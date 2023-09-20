package com.tonimurr.marvel.domain.repositories

import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter

interface MarvelRepository {

    suspend fun getMarvelCharacters(offset: Int? = null, limit: Int? = null): ListDataResponse<MarvelCharacter>

}