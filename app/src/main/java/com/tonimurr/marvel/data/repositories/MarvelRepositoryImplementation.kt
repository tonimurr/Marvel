package com.tonimurr.marvel.data.repositories

import com.tonimurr.marvel.data.remote.AppApi
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val _api: AppApi
) : MarvelRepository {

    override suspend fun getMarvelCharacters(offset: Int?, limit: Int?): ListDataResponse<MarvelCharacter> {
        val response = _api.getMarvelCharacters(offset, limit)
        val marvelCharacters = ArrayList<MarvelCharacter>()
        for(mcDTO in response.data.results) {
            var thumbnailURL = ""
            mcDTO.thumbnail?.let {
                thumbnailURL = "${it.path}.${it.extension}"
            }
            val mc = MarvelCharacter(
                mcDTO.id ?: -1,
                mcDTO.name ?: "",
                mcDTO.description ?: "",
                thumbnailURL
            )
            if(mc.id != -1) {
                marvelCharacters.add(mc)
            }
        }
        var hasMore = false
        if(response.data.offset != null && response.data.limit != null && response.data.total != null) {
            val totalLoaded = response.data.offset + response.data.limit
            hasMore = totalLoaded < response.data.total
        }
        return ListDataResponse(
            marvelCharacters,
            hasMore
        )
    }

}