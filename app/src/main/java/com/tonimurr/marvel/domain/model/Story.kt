package com.tonimurr.marvel.domain.model

data class Story(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnailURL: String,
    val originalIssue: Resource?
)
