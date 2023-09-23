package com.tonimurr.marvel.data.model

data class SeriesDTO(
    val id: Long = 0,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlDTO>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: ImageDTO?,
    val next: ResourceDTO?,
    val previous: ResourceDTO?
)
