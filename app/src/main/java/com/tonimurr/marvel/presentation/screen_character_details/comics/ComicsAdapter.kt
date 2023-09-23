package com.tonimurr.marvel.presentation.screen_character_details.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tonimurr.marvel.databinding.ViewHolderComicBinding
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.presentation.screen_character_details.CharacterDetailsBaseAdapter

class ComicsAdapter : CharacterDetailsBaseAdapter<Comic, ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(ViewHolderComicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


}