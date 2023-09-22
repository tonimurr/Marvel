package com.tonimurr.marvel.data.mappers

import com.tonimurr.marvel.common.toDate
import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.base.toUrl
import com.tonimurr.marvel.data.model.EventDTO
import com.tonimurr.marvel.domain.model.Event

class EventMapper : BaseMapper<Event, EventDTO> {
    override fun mapToDomain(item: EventDTO): Event {
        return Event(
            item.id,
            item.title ?: "",
            item.description ?: "",
            item.start?.toDate("yyyy-mm-dd"),
            item.end?.toDate("yyyy-mm-dd"),
            item.thumbnail?.toUrl() ?: ""
        )
    }

    override fun mapToDTO(item: Event): EventDTO {
        TODO("Not yet implemented, because we are not passing anything to the backend and to save time :)")
    }
}