package com.tonimurr.marvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "Comics",
    foreignKeys = [
        ForeignKey(
            entity = MarvelCharacterDTO::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("characterId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ComicsDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1,
    val digitalId: Long? = null,
    val title: String? = null,
    val issueNumber: Int? = null,
    val variantDescription: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val isbn: String? = null,
    val upc: String? = null,
    val diamondCode: String? = null,
    val ean: String? = null,
    val issn: String? = null,
    val format: String? = null,
    val pageCount: Int? = null,
    val resourceURI: String? = null,
    @Embedded
    val thumbnail: ImageDTO? = null,
    val textObjects: List<TextObjectDTO>? = null,
    val urls: List<UrlDTO>? = null,
    val images: List<ImageDTO>? = null,
    val dates: List<DateDTO>? = null,
    @Embedded
    val series: ResourceDTO? = null,
    @ColumnInfo(index = true)
    var characterId: Long = -1
)