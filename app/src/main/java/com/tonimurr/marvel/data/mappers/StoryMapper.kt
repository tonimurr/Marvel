package com.tonimurr.marvel.data.mappers

import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.base.toUrl
import com.tonimurr.marvel.data.model.StoryDTO
import com.tonimurr.marvel.domain.model.Resource
import com.tonimurr.marvel.domain.model.Story

class StoryMapper : BaseMapper<Story, StoryDTO> {
    override fun mapToDomain(item: StoryDTO): Story {
        var resource: Resource? = null
        item.originalIssue?.let {
            resource = ResourceMapper().mapToDomain(it)
        }
        return Story(
            item.id,
            item.title ?: "",
            item.description ?: "",
            item.thumbnail?.toUrl() ?: "",
            resource
        )
    }
}