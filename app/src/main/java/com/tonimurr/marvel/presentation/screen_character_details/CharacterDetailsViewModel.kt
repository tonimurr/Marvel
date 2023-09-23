package com.tonimurr.marvel.presentation.screen_character_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.model.Series
import com.tonimurr.marvel.domain.model.Story
import com.tonimurr.marvel.domain.usecases.GetCharacterComicsUseCase
import com.tonimurr.marvel.domain.usecases.GetCharacterEventsUseCase
import com.tonimurr.marvel.domain.usecases.GetCharacterSeriesUseCase
import com.tonimurr.marvel.domain.usecases.GetCharacterStoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val _getCharacterComicsUseCase: GetCharacterComicsUseCase,
    private val _getCharacterEventsUseCase: GetCharacterEventsUseCase,
    private val _getCharacterSeriesUseCase: GetCharacterSeriesUseCase,
    private val _getCharacterStoriesUseCase: GetCharacterStoriesUseCase
) : ViewModel() {

    enum class DetailsLoadingType {
        LOADING_EVENTS,
        LOADING_STORIES,
        LOADING_SERIES,
        LOADING_COMICS
    }

    private val _offset = 0
    private val _limit = 3

    private val _liveMarvelCharacter: MutableLiveData<MarvelCharacter> = MutableLiveData(null)
    private val _liveCharacterComics: MutableLiveData<List<Comic>> = MutableLiveData(null)
    private val _liveCharacterEvents: MutableLiveData<List<Event>> = MutableLiveData(null)
    private val _liveCharacterSeries: MutableLiveData<List<Series>> = MutableLiveData(null)
    private val _liveCharacterStories: MutableLiveData<List<Story>> = MutableLiveData(null)
    private val _liveLoading: MutableLiveData<Pair<DetailsLoadingType, Boolean>> = MutableLiveData(null)

    fun fetchCharacterDetails(character: MarvelCharacter) {
        val savedCharacter = _liveMarvelCharacter.value
        if (savedCharacter == null || savedCharacter.id != character.id) {
            _liveMarvelCharacter.value = character
            fetchComics()
            fetchEvents()
            fetchSeries()
            fetchStories()
        }
    }

    //#region liveData getters

    fun liveMarvelCharacter(): LiveData<MarvelCharacter> = _liveMarvelCharacter
    fun liveCharacterComics(): LiveData<List<Comic>> = _liveCharacterComics
    fun liveCharacterStories(): LiveData<List<Story>> = _liveCharacterStories
    fun liveCharacterEvents(): LiveData<List<Event>> = _liveCharacterEvents
    fun liveCharacterSeries(): LiveData<List<Series>> = _liveCharacterSeries
    fun liveLoading(): LiveData<Pair<DetailsLoadingType, Boolean>> = _liveLoading

    //endregion

    //#region fetchFunctions
    private fun fetchComics() {
        _liveMarvelCharacter.value?.let { character ->
            _getCharacterComicsUseCase(character.id, _offset, _limit).onEach { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_COMICS, true))
                    }
                    is Resource.Success -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_COMICS, false))
                        resource.data?.let {
                            _liveCharacterComics.postValue(it.data)
                        }
                    }
                    is Resource.Error -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_COMICS, false))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchEvents() {
        _liveMarvelCharacter.value?.let { character ->
            _getCharacterEventsUseCase(character.id, _offset, _limit).onEach { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_EVENTS, true))
                    }
                    is Resource.Success -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_EVENTS, false))
                        resource.data?.let {
                            _liveCharacterEvents.postValue(it.data)
                        }
                    }
                    is Resource.Error -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_EVENTS, false))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchSeries() {
        _liveMarvelCharacter.value?.let { character ->
            _getCharacterSeriesUseCase(character.id, _offset, _limit).onEach { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_SERIES, true))
                    }
                    is Resource.Success -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_SERIES, false))
                        resource.data?.let {
                            _liveCharacterSeries.postValue(it.data)
                        }
                    }
                    is Resource.Error -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_SERIES, false))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchStories() {
        _liveMarvelCharacter.value?.let { character ->
            _getCharacterStoriesUseCase(character.id, _offset, _limit).onEach { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_STORIES, true))
                    }
                    is Resource.Success -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_STORIES, false))
                        resource.data?.let {
                            _liveCharacterStories.postValue(it.data)
                        }
                    }
                    is Resource.Error -> {
                        _liveLoading.postValue(Pair(DetailsLoadingType.LOADING_STORIES, false))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    //endregion

}