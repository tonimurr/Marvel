package com.tonimurr.marvel.data.mappers

import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.model.ResourceDTO
import com.tonimurr.marvel.domain.model.Resource

class ResourceMapper : BaseMapper<Resource, ResourceDTO> {
    override fun mapToDomain(item: ResourceDTO): Resource {
        return Resource(
            item.resourceURI ?: "",
            item.name ?: "",
            item.type ?: ""
        )
    }
}