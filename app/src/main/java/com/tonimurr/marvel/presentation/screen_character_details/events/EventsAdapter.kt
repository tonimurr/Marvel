package com.tonimurr.marvel.presentation.screen_character_details.events

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tonimurr.marvel.databinding.ViewHolderEventBinding
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.presentation.screen_character_details.CharacterDetailsBaseAdapter

class EventsAdapter : CharacterDetailsBaseAdapter<Event, EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(ViewHolderEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}