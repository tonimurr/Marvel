package com.tonimurr.marvel.data.base

import com.tonimurr.marvel.data.model.ImageDTO

fun ImageDTO.toUrl(): String {
    return "${path}.${extension}"
}