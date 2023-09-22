package com.tonimurr.marvel.data.mappers

import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.base.toUrl
import com.tonimurr.marvel.data.model.MarvelCharacterDTO
import com.tonimurr.marvel.domain.model.MarvelCharacter

class MarvelCharacterMapper : BaseMapper<MarvelCharacter, MarvelCharacterDTO> {
    override fun mapToDomain(item: MarvelCharacterDTO): MarvelCharacter {
        return MarvelCharacter(
            item.id,
            item.name ?: "",
            item.description ?: "",
            item.thumbnail?.toUrl() ?: ""
        )
    }

    override fun mapToDTO(item: MarvelCharacter): MarvelCharacterDTO {
        TODO("Not yet implemented, because we are not passing anything to the backend and to save time :)")
    }
}