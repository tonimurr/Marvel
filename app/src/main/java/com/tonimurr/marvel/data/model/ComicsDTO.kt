package com.tonimurr.marvel.data.model

data class ComicsDTO(
    val id: Long = 0,
    val digitalId: Long?,
    val title: String?,
    val issueNumber: Int?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<TextObjectDTO>?,
    val resourceURI: String?,
    val urls: List<UrlDTO>?,
    val series: List<ResourceDTO>?,
    val dates: List<DateDTO>?,
    val thumbnail: ImageDTO?,
    val images: List<ImageDTO>?
)