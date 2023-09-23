package com.tonimurr.marvel.domain.repositories

import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.model.Series
import com.tonimurr.marvel.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getMarvelCharacters(offset: Int? = null, limit: Int? = null): Flow<ListDataResponse<MarvelCharacter>>

    suspend fun getCharacterComics(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<ListDataResponse<Comic>>

    suspend fun getCharacterEvents(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<ListDataResponse<Event>>

    suspend fun getCharacterSeries(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<ListDataResponse<Series>>

    suspend fun getCharacterStories(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<ListDataResponse<Story>>

}