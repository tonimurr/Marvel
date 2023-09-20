package com.tonimurr.marvel.presentation.screen_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.domain.usecases.GetMarvelCharactersUseCase
import com.tonimurr.marvel.presentation.uimodels.LoadingUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val _getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

    private val _liveMarvelCharacters: MutableLiveData<List<Any>> = MutableLiveData(null)
    private val _liveProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _offset = 0
    private val _limit = 20

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        _getMarvelCharactersUseCase(_offset, _limit).onEach { resource ->
            when(resource) {
                is Resource.Loading -> {
                    showHideInitialLoader(true)
                }
                is Resource.Success -> {
                    showHideInitialLoader(false)
                    resource.data?.let {
                        _offset += _limit
                        val existingMarvelCharacters = mutableListOf<Any>()
                        _liveMarvelCharacters.value?.let { oldList ->
                            existingMarvelCharacters.addAll(oldList)
                        }
                        if(existingMarvelCharacters.isNotEmpty() && existingMarvelCharacters.last() is LoadingUIModel) {
                            existingMarvelCharacters.removeLast()
                        }
                        existingMarvelCharacters.addAll(it.data) //new data
                        if(it.hasMore){
                            existingMarvelCharacters.add(LoadingUIModel())
                        }
                        _liveMarvelCharacters.postValue(existingMarvelCharacters)
                    }
                }
                is Resource.Error -> {
                    showHideInitialLoader(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun showHideInitialLoader(show: Boolean) {
        if(_offset == 0 && show) {
            _liveProgressBar.postValue(true)
        }else{
            _liveProgressBar.postValue(false)
        }
    }

    fun liveMarvelCharacters(): LiveData<List<Any>> {
        return _liveMarvelCharacters
    }

    fun liveProgressBar(): LiveData<Boolean> {
        return _liveProgressBar
    }

}