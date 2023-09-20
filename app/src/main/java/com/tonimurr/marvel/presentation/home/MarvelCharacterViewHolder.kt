package com.tonimurr.marvel.presentation.home

import com.bumptech.glide.Glide
import com.tonimurr.marvel.databinding.ViewHolderMarvelCharacterBinding
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.presentation.base.MarvelViewHolder

class MarvelCharacterViewHolder(val binding: ViewHolderMarvelCharacterBinding) : MarvelViewHolder<MarvelCharacter>(binding.root) {

    override fun onBindView(item: MarvelCharacter, position: Int) {
        binding.textViewCharacter.text = item.name
        Glide.with(binding.root).load(item.thumbnailUrl).centerCrop().into(binding.imageViewCharacter)
    }

}