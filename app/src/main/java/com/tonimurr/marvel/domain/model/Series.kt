package com.tonimurr.marvel.domain.model

data class Series(
    val id: Long,
    val title: String,
    val description: String,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val thumbnailURL: String
)
