package com.tonimurr.marvel.data.repositories

import com.tonimurr.marvel.data.db.AppDatabase
import com.tonimurr.marvel.data.model.MarvelCharacterDTO
import com.tonimurr.marvel.data.remote.AppApi
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
            val dbMarvelCharacters = processMarvelCharactersData(_db.marvelCharacterDao().getAll())
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
        val marvelCharacters = processMarvelCharactersData(response.data.results)
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

    private fun processMarvelCharactersData(data: List<MarvelCharacterDTO>): ArrayList<MarvelCharacter> {
        val marvelCharacters = ArrayList<MarvelCharacter>()
        for (mcDTO in data) {
            var thumbnailURL = ""
            mcDTO.thumbnail?.let {
                thumbnailURL = "${it.path}.${it.extension}"
            }
            val mc = MarvelCharacter(
                mcDTO.id,
                mcDTO.name ?: "",
                mcDTO.description ?: "",
                thumbnailURL
            )
            if (mc.id != -1L) {
                marvelCharacters.add(mc)
            }
        }
        return marvelCharacters
    }

}