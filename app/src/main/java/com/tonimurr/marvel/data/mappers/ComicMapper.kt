package com.tonimurr.marvel.data.mappers

import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.base.toStandardLargeURL
import com.tonimurr.marvel.data.base.toUrl
import com.tonimurr.marvel.data.model.ComicsDTO
import com.tonimurr.marvel.domain.model.Comic

class ComicMapper: BaseMapper<Comic, ComicsDTO> {
    override fun mapToDomain(item: ComicsDTO): Comic {
        return Comic(
            item.id,
            item.title ?: "",
            item.thumbnail?.toStandardLargeURL() ?: ""
        )
    }

}