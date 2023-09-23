package com.tonimurr.marvel.presentation.screen_character_details.comics

import android.view.View
import com.bumptech.glide.Glide
import com.tonimurr.marvel.databinding.ViewHolderComicBinding
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.presentation.base.MarvelViewHolder

class ComicViewHolder(val bindings: ViewHolderComicBinding) : MarvelViewHolder<Comic>(bindings.root) {
    override fun onBindView(item: Comic, position: Int) {
        bindings.textViewTitle.text = item.title
        Glide.with(itemView.context).load(item.thumbnailUrl).into(bindings.imageView)
    }
}