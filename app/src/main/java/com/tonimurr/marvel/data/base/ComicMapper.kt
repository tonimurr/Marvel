package com.tonimurr.marvel.data.base

import com.tonimurr.marvel.data.model.ComicsDTO
import com.tonimurr.marvel.domain.model.Comic

class ComicMapper: BaseMapper<Comic, ComicsDTO> {
    override fun mapToDomain(item: ComicsDTO): Comic {
        return Comic(
            item.id,
            item.title ?: "",
            item.thumbnail?.toUrl() ?: ""
        )
    }

    override fun mapToDTO(item: Comic): ComicsDTO {
        TODO("Not yet implemented, because we are not passing anything to the backend and to save time :)")
    }
}