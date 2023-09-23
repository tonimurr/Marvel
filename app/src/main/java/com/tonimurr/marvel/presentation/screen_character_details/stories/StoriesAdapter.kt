package com.tonimurr.marvel.presentation.screen_character_details.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tonimurr.marvel.databinding.ViewHolderStoryBinding
import com.tonimurr.marvel.domain.model.Story
import com.tonimurr.marvel.presentation.screen_character_details.CharacterDetailsBaseAdapter

class StoriesAdapter : CharacterDetailsBaseAdapter<Story, StoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(ViewHolderStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}