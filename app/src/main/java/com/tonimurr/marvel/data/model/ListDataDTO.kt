package com.tonimurr.marvel.data.model

class ListDataDTO<T>(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<T>
)