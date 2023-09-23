package com.tonimurr.marvel.presentation.screen_character_details.events

import com.bumptech.glide.Glide
import com.tonimurr.marvel.R
import com.tonimurr.marvel.common.formatString
import com.tonimurr.marvel.databinding.ViewHolderEventBinding
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.presentation.base.MarvelViewHolder

class EventViewHolder(val bindings: ViewHolderEventBinding) : MarvelViewHolder<Event>(bindings.root) {
    override fun onBindView(item: Event, position: Int) {
        Glide.with(itemView).load(item.thumbnailURL).into(bindings.imageView)
        bindings.textViewTitle.text = item.title
        bindings.textViewStartDate.text = itemView.context.getString(R.string.start_date_value, item.startDate?.formatString("dd MMMM yyyy"))
        bindings.textViewEndDate.text = itemView.context.getString(R.string.end_date_value,item.endDate?.formatString("dd MMMM yyyy"))
    }
}