package com.tonimurr.marvel.presentation.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tonimurr.marvel.databinding.ViewHolderLoadingBinding
import com.tonimurr.marvel.presentation.base.MarvelViewHolder
import com.tonimurr.marvel.presentation.uimodels.LoadingUIModel

class LoadingViewHolder(val binding: ViewHolderLoadingBinding) : MarvelViewHolder<LoadingUIModel>(
    binding.root
) {

    override fun onBindView(item: LoadingUIModel, position: Int) {

    }

}