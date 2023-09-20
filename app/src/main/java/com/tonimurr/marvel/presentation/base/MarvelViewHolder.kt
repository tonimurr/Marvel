package com.tonimurr.marvel.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class MarvelViewHolder<T>(view: View) : ViewHolder(view) {

    abstract fun onBindView(item: T, position: Int)

}