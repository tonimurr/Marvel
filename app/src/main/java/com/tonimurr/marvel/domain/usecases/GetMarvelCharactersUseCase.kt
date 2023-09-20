package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
) : UseCase<List<MarvelCharacter>>() {

    override fun tagName(): String = "GetMarvelCharactersUseCase"

    operator fun invoke(): Flow<Resource<List<MarvelCharacter>>> = baseFlow {
        it.emit(Resource.Loading())
        val marvelCharacters = repository.getMarvelCharacters()
        it.emit(Resource.Success(marvelCharacters))
    }

}