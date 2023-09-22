package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterComicsUseCase @Inject constructor(
    private val repository: MarvelRepository
) : UseCase<ListDataResponse<Comic>>() {

    override fun tagName(): String = "GetCharacterComicsUseCase"

    operator fun invoke(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<Resource<ListDataResponse<Comic>>> = baseFlow { baseFlow ->
        baseFlow.emit(Resource.Loading())
        repository.getCharacterComics(characterId, offset, limit).collect {
            baseFlow.emit(Resource.Success(it))
        }
    }

}