package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.Series
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GetCharacterSeriesUseCase @Inject constructor(
    private val _repository: MarvelRepository
) : UseCase<ListDataResponse<Series>>() {

    override fun tagName(): String = "GetCharacterSeriesUseCase"

    operator fun invoke(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<Resource<ListDataResponse<Series>>> = baseFlow { baseFlow ->
        baseFlow.emit(Resource.Loading())
        _repository.getCharacterSeries(characterId, offset, limit).collect {
            baseFlow.emit(Resource.Success(it))
        }
    }

}