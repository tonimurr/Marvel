package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterEventsUseCase @Inject constructor(
    private val _repository: MarvelRepository
): UseCase<ListDataResponse<Event>>() {

    override fun tagName(): String = "GetCharacterEventsUseCase"

    operator fun invoke(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<Resource<ListDataResponse<Event>>> = baseFlow { baseFlow ->
        baseFlow.emit(Resource.Loading())
        _repository.getCharacterEvents(characterId, offset, limit).collect {
            baseFlow.emit(Resource.Success(it))
        }
    }

}