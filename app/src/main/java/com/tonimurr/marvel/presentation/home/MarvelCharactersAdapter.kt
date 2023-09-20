package com.tonimurr.marvel.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.tonimurr.marvel.databinding.ViewHolderLoadingBinding
import com.tonimurr.marvel.databinding.ViewHolderMarvelCharacterBinding
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.presentation.base.MarvelViewHolder
import com.tonimurr.marvel.presentation.common.LoadingViewHolder
import com.tonimurr.marvel.presentation.uimodels.LoadingUIModel

class MarvelCharactersAdapter : Adapter<MarvelViewHolder<*>>() {

    interface MarvelCharactersAdapterInterface {
        fun triggerLoadMore()
    }

    private val _marvelCharacters: ArrayList<Any> = ArrayList()
    private var _marvelCharactersAdapterInterface: MarvelCharactersAdapterInterface? = null

    enum class ViewTypes {
        MARVEL_CHARACTER,
        LOADING
    }

    override fun getItemViewType(position: Int): Int {
        return when(_marvelCharacters[position]) {
            is MarvelCharacter -> {
                ViewTypes.MARVEL_CHARACTER.ordinal
            }

            is LoadingUIModel -> {
                ViewTypes.LOADING.ordinal
            }

            else -> {
                super.getItemViewType(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder<*> {
        return when(ViewTypes.values()[viewType]) {
            ViewTypes.MARVEL_CHARACTER -> {
                MarvelCharacterViewHolder(ViewHolderMarvelCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

            ViewTypes.LOADING -> {
                LoadingViewHolder(ViewHolderLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return _marvelCharacters.size
    }

    override fun onBindViewHolder(holder: MarvelViewHolder<*>, position: Int) {
        val adapterPosition = holder.adapterPosition
        val item = _marvelCharacters[adapterPosition]
        when(holder) {
            is MarvelCharacterViewHolder -> {
                holder.onBindView(item as MarvelCharacter, adapterPosition)
            }
            is LoadingViewHolder -> {
                holder.onBindView(item as LoadingUIModel, adapterPosition)
                _marvelCharactersAdapterInterface?.triggerLoadMore()
            }
        }
    }

    fun setMarvelCharacters(data: List<Any>) {
        _marvelCharacters.clear()
        _marvelCharacters.addAll(data)
        notifyDataSetChanged()
    }

    fun addMarvelCharacters(data: List<Any>) {
        removeLoadMore()
        val fromIndex = _marvelCharacters.size
        val toIndex = data.size
        _marvelCharacters.addAll(data)
        notifyItemRangeInserted(fromIndex, toIndex)
    }

    fun removeLoadMore() {
        if(_marvelCharacters.isNotEmpty() && _marvelCharacters.last() is LoadingUIModel) {
            val lastIndex = _marvelCharacters.lastIndex
            _marvelCharacters.removeLast()
            notifyItemRemoved(lastIndex)
        }
    }

    fun setMarvelCharactersAdapterInterface(marvelCharactersAdapterInterface: MarvelCharactersAdapterInterface) {
        _marvelCharactersAdapterInterface = marvelCharactersAdapterInterface
    }

}

