package com.tonimurr.marvel.data.model

data class MarvelCharacterDTO(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: ThumbnailDTO?,
    val comics: DetailsHolderDTO<ComicsDTO>?,
    val series: DetailsHolderDTO<SeriesDTO>?,
    val stories: DetailsHolderDTO<StoriesDTO>?,
    val events: DetailsHolderDTO<EventDTO>?,
    val urls: List<UrlDTO>?
)