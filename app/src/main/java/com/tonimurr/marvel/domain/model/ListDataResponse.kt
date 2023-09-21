package com.tonimurr.marvel.domain.model

data class ListDataResponse<T>(
    val data: List<T>,
    val total: Int,
    val offset: Int,
    val fromCache: Boolean,
    val hasMore: Boolean = false
)