package com.tonimurr.marvel.domain.model

import java.util.Date

data class Event(
    val id: Long,
    val title: String,
    val description: String,
    val startDate: Date?,
    val endDate: Date?,
    val thumbnailURL: String
)
