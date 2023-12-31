package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(
    private val _repository: MarvelRepository
) : UseCase<ListDataResponse<MarvelCharacter>>() {

    override fun tagName(): String = "GetMarvelCharactersUseCase"

    operator fun invoke(offset: Int? = null, limit: Int? = null): Flow<Resource<ListDataResponse<MarvelCharacter>>> = baseFlow { baseFlow ->
        baseFlow.emit(Resource.Loading())
        _repository.getMarvelCharacters(offset, limit).collect {
            baseFlow.emit(Resource.Success(it))
        }
    }

}