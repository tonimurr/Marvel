package com.tonimurr.marvel.presentation.screen_character_details

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
        LOADING_COMICS,
        REFRESH_DATA
    }

    private val _offset = 0
    private val _limit = 3
    private val _refreshArray: ArrayList<DetailsLoadingType> = ArrayList()
    private var _isRefreshing = false

    private val _liveMarvelCharacter: MutableLiveData<MarvelCharacter> = MutableLiveData(null)
    private val _liveCharacterComics: MutableLiveData<List<Comic>> = MutableLiveData(null)
    private val _liveCharacterEvents: MutableLiveData<List<Event>> = MutableLiveData(null)
    private val _liveCharacterSeries: MutableLiveData<List<Series>> = MutableLiveData(null)
    private val _liveCharacterStories: MutableLiveData<List<Story>> = MutableLiveData(null)

    private val _liveLoadingComics: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _liveLoadingEvents: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _liveLoadingStories: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _liveLoadingSeries: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _liveLoadingRefreshData: MutableLiveData<Boolean> = MutableLiveData(false)



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

    fun refreshCharacter() {
        if(!_isRefreshing) {
            _isRefreshing = true
            _refreshArray.clear()
            _liveLoadingRefreshData.postValue(true)
            _liveMarvelCharacter.value?.let {
                fetchComics()
                fetchEvents()
                fetchSeries()
                fetchStories()
            }
        }
    }

    //#region liveData getters

    fun liveMarvelCharacter(): LiveData<MarvelCharacter> = _liveMarvelCharacter
    fun liveCharacterComics(): LiveData<List<Comic>> = _liveCharacterComics
    fun liveCharacterStories(): LiveData<List<Story>> = _liveCharacterStories
    fun liveCharacterEvents(): LiveData<List<Event>> = _liveCharacterEvents
    fun liveCharacterSeries(): LiveData<List<Series>> = _liveCharacterSeries
    fun liveLoadingRefreshData(): LiveData<Boolean> = _liveLoadingRefreshData

    fun liveLoadingComics(): LiveData<Boolean> = _liveLoadingComics
    fun liveLoadingEvents(): LiveData<Boolean> = _liveLoadingEvents
    fun liveLoadingStories(): LiveData<Boolean> = _liveLoadingStories
    fun liveLoadingSeries(): LiveData<Boolean> = _liveLoadingSeries

    //endregion

    //#region fetchFunctions
    private fun fetchComics() {
        _liveMarvelCharacter.value?.let { character ->
            _getCharacterComicsUseCase(character.id, _offset, _limit).onEach { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        if(!_isRefreshing) {
                            _liveLoadingComics.postValue(true)
                        }
                    }
                    is Resource.Success -> {
                        _refreshArray.add(DetailsLoadingType.LOADING_COMICS)
                        _liveLoadingComics.postValue(false)
                        resource.data?.let {
                            _liveCharacterComics.postValue(it.data)
                        }
                        checkAllDataRefreshed()
                    }
                    is Resource.Error -> {
                        _liveLoadingComics.postValue(false)
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
                        if(!_isRefreshing) {
                            _liveLoadingEvents.postValue(true)
                        }
                    }
                    is Resource.Success -> {
                        _refreshArray.add(DetailsLoadingType.LOADING_EVENTS)
                        _liveLoadingEvents.postValue(false)
                        resource.data?.let {
                            _liveCharacterEvents.postValue(it.data)
                        }
                        checkAllDataRefreshed()
                    }
                    is Resource.Error -> {
                        _liveLoadingEvents.postValue(false)
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
                        if(!_isRefreshing) {
                            _liveLoadingSeries.postValue(true)
                        }
                    }
                    is Resource.Success -> {
                        _refreshArray.add(DetailsLoadingType.LOADING_SERIES)
                        _liveLoadingSeries.postValue(false)
                        resource.data?.let {
                            _liveCharacterSeries.postValue(it.data)
                        }
                        checkAllDataRefreshed()
                    }
                    is Resource.Error -> {
                        _liveLoadingSeries.postValue(false)
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
                        if(!_isRefreshing) {
                            _liveLoadingStories.postValue(true)
                        }
                    }
                    is Resource.Success -> {
                        _refreshArray.add(DetailsLoadingType.LOADING_STORIES)
                        _liveLoadingStories.postValue(false)
                        resource.data?.let {
                            _liveCharacterStories.postValue(it.data)
                        }
                        checkAllDataRefreshed()
                    }
                    is Resource.Error -> {
                        _liveLoadingSeries.postValue(false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    //endregion

    private fun checkAllDataRefreshed() {
        if(_refreshArray.contains(DetailsLoadingType.LOADING_STORIES) &&
            _refreshArray.contains(DetailsLoadingType.LOADING_EVENTS) &&
            _refreshArray.contains(DetailsLoadingType.LOADING_COMICS) &&
            _refreshArray.contains(DetailsLoadingType.LOADING_SERIES) ){
            _liveLoadingRefreshData.postValue(false)
            _refreshArray.clear()
            _isRefreshing = false
        }
    }

}