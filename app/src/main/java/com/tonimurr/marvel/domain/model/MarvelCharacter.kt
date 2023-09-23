package com.tonimurr.marvel.domain.model

import java.io.Serializable

data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
) : Serializable