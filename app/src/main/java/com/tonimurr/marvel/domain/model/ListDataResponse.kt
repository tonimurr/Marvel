package com.tonimurr.marvel.domain.model

data class ListDataResponse<T>(
    val data: List<T>,
    val hasMore: Boolean = false
)