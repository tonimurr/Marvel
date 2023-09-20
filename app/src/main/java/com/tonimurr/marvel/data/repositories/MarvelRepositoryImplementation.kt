package com.tonimurr.marvel.data.repositories

import com.tonimurr.marvel.data.remote.AppApi
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val _api: AppApi
) : MarvelRepository {

    override suspend fun getMarvelCharacters(): List<MarvelCharacter> {
        val response = _api.getMarvelCharacters()
        val marvelCharacters = ArrayList<MarvelCharacter>()
        for(mcDTO in response.data.results) {
            val mc = MarvelCharacter(
                mcDTO.id ?: -1,
                mcDTO.name ?: "",
                mcDTO.description ?: "",
                mcDTO.thumbnail?.path ?: ""
            )
            if(mc.id != -1) {
                marvelCharacters.add(mc)
            }
        }
        return marvelCharacters
    }

}