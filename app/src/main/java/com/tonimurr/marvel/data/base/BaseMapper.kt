package com.tonimurr.marvel.data.base

interface BaseMapper<DOMAIN, DTO> {

    fun mapToDomain(item: DTO): DOMAIN
    fun mapToDTO(item: DOMAIN): DTO

    fun mapListToDomain(items: List<DTO>): List<DOMAIN> {
        val list = mutableListOf<DOMAIN>()
        for(item in items) {
            list.add(mapToDomain(item))
        }
        return list
    }

    fun mapListToDTO(items: List<DOMAIN>): List<DTO> {
        val list = mutableListOf<DTO>()
        for(item in items) {
            list.add(mapToDTO(item))
        }
        return list
    }

}