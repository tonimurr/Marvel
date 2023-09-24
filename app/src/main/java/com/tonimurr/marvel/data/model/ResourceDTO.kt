package com.tonimurr.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ResourceDTO(
    @SerializedName("resourceURI")
    val resURI: String?,
    val name: String?,
    val type: String?
)