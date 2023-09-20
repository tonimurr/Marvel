package com.tonimurr.marvel.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonimurr.marvel.common.Resource
import com.tonimurr.marvel.domain.usecases.GetMarvelCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val _getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

    fun fetchCharacters() {
        _getMarvelCharactersUseCase().onEach { resource ->
            when(resource) {
                is Resource.Loading -> {
                    Log.d("fetchCharacters", "Loading")
                }
                is Resource.Success -> {
                    Log.d("fetchCharacters", "Success fetched: ${resource.data?.size ?: 0}")
                }
                is Resource.Error -> {
                    Log.d("fetchCharacters", "Error: ${resource.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

}