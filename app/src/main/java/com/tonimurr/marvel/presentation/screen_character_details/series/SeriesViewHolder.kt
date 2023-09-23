package com.tonimurr.marvel.presentation.screen_character_details.series

import com.bumptech.glide.Glide
import com.tonimurr.marvel.R
import com.tonimurr.marvel.databinding.ViewHolderSeriesBinding
import com.tonimurr.marvel.domain.model.Series
import com.tonimurr.marvel.presentation.base.MarvelViewHolder

class SeriesViewHolder(private val bindings: ViewHolderSeriesBinding) : MarvelViewHolder<Series>(bindings.root) {
    override fun onBindView(item: Series, position: Int) {
        Glide.with(itemView).load(item.thumbnailURL).into(bindings.imageView)
        bindings.textViewTitle.text = item.title
        if(item.startYear != 0) {
            bindings.textViewStartDate.text =
                itemView.context.getString(R.string.start_year_value, item.startYear)
        }
        if(item.endYear != 0) {
            bindings.textViewEndDate.text =
                itemView.context.getString(R.string.end_year_value, item.endYear)
        }
        var ratings = itemView.context.getString(R.string.ratings_not_available)
        if(item.rating.isNotEmpty()){
            ratings = itemView.context.getString(R.string.ratings_value, item.rating)
        }
        bindings.textViewRatings.text = ratings
    }
}