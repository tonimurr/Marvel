package com.tonimurr.marvel.data.model

data class BaseResponse<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHtml: String?,
    val etag: String?,
    val data: ListDataDTO<T>
)
