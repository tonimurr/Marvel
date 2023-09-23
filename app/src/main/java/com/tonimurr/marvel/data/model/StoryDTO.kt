package com.tonimurr.marvel.data.model

data class StoryDTO(
    val id: Long = 0,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: ImageDTO?,
    val originalIssue: ResourceDTO?
)
