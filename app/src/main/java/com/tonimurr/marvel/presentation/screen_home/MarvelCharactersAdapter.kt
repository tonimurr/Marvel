package com.tonimurr.marvel.presentation.screen_home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.tonimurr.marvel.databinding.ViewHolderLoadingBinding
import com.tonimurr.marvel.databinding.ViewHolderMarvelCharacterBinding
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.presentation.base.MarvelViewHolder
import com.tonimurr.marvel.presentation.common.LoadingViewHolder
import com.tonimurr.marvel.presentation.uimodels.LoadingUIModel

class MarvelCharactersAdapterDiffCallback(private val oldList: List<Any>, private val newList: List<Any>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if(oldItem is MarvelCharacter && newItem is MarvelCharacter) {
            oldItem.id == newItem.id
        }else oldItem is LoadingUIModel && newItem is LoadingUIModel
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        if(oldItem is MarvelCharacter && newItem is MarvelCharacter) {
            return oldItem.name == newItem.name && oldItem.description == newItem.description && oldItem.thumbnailUrl == newItem.thumbnailUrl
        }else if(oldItem is LoadingUIModel && newItem is LoadingUIModel){
            return true
        }
        return false
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}

class MarvelCharactersAdapter : Adapter<MarvelViewHolder<*>>() {

    interface MarvelCharactersAdapterInterface {
        fun triggerLoadMore()
        fun didClickOnCharacter(view: View, character: MarvelCharacter)
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
                holder.binding.cardView.setOnClickListener {
                    _marvelCharactersAdapterInterface?.didClickOnCharacter(it, item)
                }
            }
            is LoadingViewHolder -> {
                holder.onBindView(item as LoadingUIModel, adapterPosition)
                _marvelCharactersAdapterInterface?.triggerLoadMore()
            }
        }
    }

    fun setMarvelCharacters(data: List<Any>) {
        val diffCallBack = MarvelCharactersAdapterDiffCallback(_marvelCharacters, data)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        _marvelCharacters.clear()
        _marvelCharacters.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setMarvelCharactersAdapterInterface(marvelCharactersAdapterInterface: MarvelCharactersAdapterInterface) {
        _marvelCharactersAdapterInterface = marvelCharactersAdapterInterface
    }

}

