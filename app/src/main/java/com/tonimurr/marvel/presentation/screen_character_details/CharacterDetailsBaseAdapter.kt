package com.tonimurr.marvel.presentation.screen_character_details

import androidx.recyclerview.widget.RecyclerView.Adapter
import com.tonimurr.marvel.presentation.base.MarvelViewHolder

abstract class CharacterDetailsBaseAdapter<T, VH: MarvelViewHolder<T>> : Adapter<VH>() {

    private val _items = ArrayList<T>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindView(_items[holder.adapterPosition], holder.adapterPosition)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    fun setItems(items: List<T>) {
        _items.clear()
        _items.addAll(items)
        //it is more optimal to use DiffUtils like in the first page
        notifyDataSetChanged()
    }

}