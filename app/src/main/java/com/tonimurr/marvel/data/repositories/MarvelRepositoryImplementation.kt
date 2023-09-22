package com.tonimurr.marvel.data.repositories

import com.tonimurr.marvel.data.base.ComicMapper
import com.tonimurr.marvel.data.base.MarvelCharacterMapper
import com.tonimurr.marvel.data.db.AppDatabase
import com.tonimurr.marvel.data.model.MarvelCharacterDTO
import com.tonimurr.marvel.data.remote.AppApi
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val _api: AppApi,
    private val _db: AppDatabase,
) : MarvelRepository {

    override suspend fun getMarvelCharacters(
        offset: Int?,
        limit: Int?
    ): Flow<ListDataResponse<MarvelCharacter>> = flow {
        if (offset == 0) {
            val dbMarvelCharacters = MarvelCharacterMapper().mapListToDomain(_db.marvelCharacterDao().getAll())
            if(dbMarvelCharacters.isNotEmpty()) {
                emit(
                    ListDataResponse(
                        dbMarvelCharacters,
                        dbMarvelCharacters.size,
                        offset,
                        fromCache = true,
                        hasMore = false
                    )
                )
            }
        }
        val response = _api.getMarvelCharacters(offset, limit)
        //Caching the marvel characters
        if (offset == 0) {
            _db.marvelCharacterDao().insert(response.data.results)
        }
        val marvelCharacters = MarvelCharacterMapper().mapListToDomain(response.data.results)
        var hasMore = false
        if (response.data.offset != null && response.data.limit != null && response.data.total != null) {
            val totalLoaded = response.data.offset + response.data.limit
            hasMore = totalLoaded < response.data.total
        }
        emit(
            ListDataResponse(
                marvelCharacters,
                marvelCharacters.size,
                offset ?: 0,
                false,
                hasMore
            )
        )
    }

    override suspend fun getCharacterComics(
        characterId: Long,
        offset: Int?,
        limit: Int?
    ): Flow<ListDataResponse<Comic>> = flow {
        val response = _api.getCharacterComics(characterId, offset, limit)
        val comics = ComicMapper().mapListToDomain(response.data.results)
        emit(
            ListDataResponse(
                comics,
                comics.size,
                offset ?: 0,
            )
        )
    }


}