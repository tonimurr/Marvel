package com.tonimurr.marvel.presentation.screen_character_details.stories

import com.bumptech.glide.Glide
import com.tonimurr.marvel.R
import com.tonimurr.marvel.databinding.ViewHolderStoryBinding
import com.tonimurr.marvel.domain.model.Story
import com.tonimurr.marvel.presentation.base.MarvelViewHolder

class StoryViewHolder(val bindings: ViewHolderStoryBinding) : MarvelViewHolder<Story>(bindings.root) {
    override fun onBindView(item: Story, position: Int) {
        Glide.with(itemView).load(item.thumbnailURL).centerCrop().into(bindings.imageView)
        bindings.textViewTitle.text = item.title
    }
}