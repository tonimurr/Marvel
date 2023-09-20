package com.tonimurr.marvel.data.model

data class DetailsHolderDTO<T>(
    val available: Int?,
    val collectionURI: String?,
    val items: List<T>?,
    val returned: Int?
)
