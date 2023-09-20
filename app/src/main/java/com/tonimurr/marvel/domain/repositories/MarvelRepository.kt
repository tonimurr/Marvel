package com.tonimurr.marvel.domain.repositories

import com.tonimurr.marvel.domain.model.MarvelCharacter

interface MarvelRepository {

    suspend fun getMarvelCharacters(): List<MarvelCharacter>

}