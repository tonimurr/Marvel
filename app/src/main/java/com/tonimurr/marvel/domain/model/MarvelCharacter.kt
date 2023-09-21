package com.tonimurr.marvel.domain.model

data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)