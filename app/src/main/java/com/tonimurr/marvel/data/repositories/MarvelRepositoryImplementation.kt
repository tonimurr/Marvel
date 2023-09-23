package com.tonimurr.marvel.data.repositories

import com.tonimurr.marvel.data.mappers.ComicMapper
import com.tonimurr.marvel.data.mappers.MarvelCharacterMapper
import com.tonimurr.marvel.data.db.AppDatabase
import com.tonimurr.marvel.data.mappers.EventMapper
import com.tonimurr.marvel.data.mappers.SeriesMapper
import com.tonimurr.marvel.data.mappers.StoryMapper
import com.tonimurr.marvel.data.remote.AppApi
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.model.Series
import com.tonimurr.marvel.domain.model.Story
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
        if(response.code == 200) {
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
    }

    override suspend fun getCharacterComics(
        characterId: Long,
        offset: Int?,
        limit: Int?
    ): Flow<ListDataResponse<Comic>> = flow {
        val response = _api.getCharacterComics(characterId, offset, limit)
        if(response.code == 200) {
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

    override suspend fun getCharacterEvents(
        characterId: Long,
        offset: Int?,
        limit: Int?
    ): Flow<ListDataResponse<Event>> = flow {
        val response = _api.getCharacterEvents(characterId, offset, limit)
        if(response.code == 200) {
            val events = EventMapper().mapListToDomain(response.data.results)
            emit(
                ListDataResponse(
                    events,
                    events.size,
                    offset ?: 0
                )
            )
        }
    }

    override suspend fun getCharacterSeries(
        characterId: Long,
        offset: Int?,
        limit: Int?
    ): Flow<ListDataResponse<Series>> = flow {
        val response = _api.getCharacterSeries(characterId, offset, limit)
        if(response.code == 200) {
            val series = SeriesMapper().mapListToDomain(response.data.results)
            emit(
                ListDataResponse(
                    series,
                    series.size,
                    offset ?: 0
                )
            )
        }
    }

    override suspend fun getCharacterStories(
        characterId: Long,
        offset: Int?,
        limit: Int?
    ): Flow<ListDataResponse<Story>> = flow {
        val response = _api.getCharacterStories(characterId, offset, limit)
        if(response.code == 200) {
            val stories = StoryMapper().mapListToDomain(response.data.results)
            emit(
                ListDataResponse(
                    stories,
                    stories.size,
                    offset ?: 0
                )
            )
        }
    }


}