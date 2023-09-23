package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.data.base.BaseMapper
import com.tonimurr.marvel.data.model.StoryDTO
import com.tonimurr.marvel.domain.model.ListDataResponse
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.model.Story
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterStoriesUseCase @Inject constructor(
    private val _repository: MarvelRepository
): UseCase<ListDataResponse<Story>>() {

    override fun tagName(): String = "GetCharacterStoriesUseCase"

    operator fun invoke(characterId: Long, offset: Int? = null, limit: Int? = null): Flow<Resource<ListDataResponse<Story>>> = baseFlow { baseFlow ->
        baseFlow.emit(Resource.Loading())
        _repository.getCharacterStories(characterId, offset, limit).collect {
            baseFlow.emit(Resource.Success(it))
        }
    }

}