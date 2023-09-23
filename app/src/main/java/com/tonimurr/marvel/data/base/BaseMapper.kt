package com.tonimurr.marvel.data.base

interface BaseMapper<DOMAIN, DTO> {

    fun mapToDomain(item: DTO): DOMAIN

    fun mapListToDomain(items: List<DTO>): List<DOMAIN> {
        val list = mutableListOf<DOMAIN>()
        for(item in items) {
            list.add(mapToDomain(item))
        }
        return list
    }

    //todo: if we need to send data to the BE we need to implement mapToDTO and mapListToDTO functions in each mapper

}