package com.tonimurr.marvel.presentation.screen_character_details.series

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tonimurr.marvel.databinding.ViewHolderSeriesBinding
import com.tonimurr.marvel.domain.model.Series
import com.tonimurr.marvel.presentation.screen_character_details.CharacterDetailsBaseAdapter

class SeriesAdapter : CharacterDetailsBaseAdapter<Series, SeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(ViewHolderSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}