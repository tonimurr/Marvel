package com.tonimurr.marvel.data.mappers

import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.base.toUrl
import com.tonimurr.marvel.data.model.SeriesDTO
import com.tonimurr.marvel.domain.model.Series

class SeriesMapper : BaseMapper<Series, SeriesDTO> {
    override fun mapToDomain(item: SeriesDTO): Series {
        return Series(
            item.id,
            item.title ?: "",
            item.description ?: "",
            item.startYear ?: 0,
            item.endYear ?: 0,
            item.rating ?: "",
            item.thumbnail?.toUrl() ?: ""
        )
    }

}