package com.tonimurr.marvel.data.model

data class EventDTO(
    val id: Long = 0,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlDTO>?,
    val modified: String?,
    val start: String?,
    val end: String?,
    val thumbnail: ImageDTO?,
    val next: ResourceDTO?,
    val previous: ResourceDTO?
)
