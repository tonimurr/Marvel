package com.tonimurr.marvel.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "MarvelCharacter")
data class MarvelCharacterDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    @Embedded
    val thumbnail: ThumbnailDTO? = null,
) {
    @Ignore
    val comics: DetailsHolderDTO<ComicsDTO>? = null
    @Ignore
    val series: DetailsHolderDTO<SeriesDTO>? = null
    @Ignore
    val stories: DetailsHolderDTO<StoriesDTO>? = null
    @Ignore
    val events: DetailsHolderDTO<EventDTO>? = null
    @Ignore
    val urls: List<UrlDTO>? = null
}