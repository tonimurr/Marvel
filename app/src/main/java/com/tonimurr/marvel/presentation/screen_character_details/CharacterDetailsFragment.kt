package com.tonimurr.marvel.presentation.screen_character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tonimurr.marvel.R
import com.tonimurr.marvel.databinding.FragmentCharacterDetailsBinding
import com.tonimurr.marvel.domain.model.Comic
import com.tonimurr.marvel.domain.model.Event
import com.tonimurr.marvel.presentation.base.MarvelFragment
import com.tonimurr.marvel.presentation.screen_character_details.comics.ComicsAdapter
import com.tonimurr.marvel.presentation.screen_character_details.events.EventsAdapter
import com.tonimurr.marvel.presentation.screen_character_details.series.SeriesAdapter
import com.tonimurr.marvel.presentation.screen_character_details.stories.StoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : MarvelFragment() {

    private lateinit var _binding: FragmentCharacterDetailsBinding
    private val _args: CharacterDetailsFragmentArgs by navArgs()
    private val _viewModel: CharacterDetailsViewModel by viewModels()

    private var _comicsAdapter: ComicsAdapter? = null
    private var _eventsAdapter: EventsAdapter? = null
    private var _storiesAdapter: StoriesAdapter? = null
    private var _seriesAdapter: SeriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        _args.marvelCharacter?.let {
            _viewModel.fetchCharacterDetails(it)
        }

        _viewModel.liveMarvelCharacter().observe(viewLifecycleOwner) {
            if(it != null) {
                _binding.textViewCharacterId.text = getString(R.string.id_value, it.id)
                _binding.textViewCharacterName.text = it.name
                Glide.with(requireContext()).load(it.thumbnailUrl).centerCrop().into(_binding.imageViewCharacter)
            }
        }

        _viewModel.liveCharacterComics().observe(viewLifecycleOwner) {
            displayItemLogic(
                it,
                _binding.recyclerViewComics,
                _binding.textViewComicsEmpty,
                _comicsAdapter
            )
        }

        _viewModel.liveCharacterEvents().observe(viewLifecycleOwner) {
            displayItemLogic(
                it,
                _binding.recyclerViewEvents,
                _binding.textViewEventsEmpty,
                _eventsAdapter
            )
        }

        _viewModel.liveCharacterStories().observe(viewLifecycleOwner) {
            displayItemLogic(
                it,
                _binding.recyclerViewStories,
                _binding.textViewStoriesEmpty,
                _storiesAdapter
            )
        }

        _viewModel.liveCharacterSeries().observe(viewLifecycleOwner) {
            displayItemLogic(
                it,
                _binding.recyclerViewSeries,
                _binding.textViewSeriesEmpty,
                _seriesAdapter
            )
        }

        _viewModel.liveLoading().observe(viewLifecycleOwner) {
            if(it != null) {
                when(it.first) {
                    CharacterDetailsViewModel.DetailsLoadingType.LOADING_COMICS -> {
                        showHideProgressView(_binding.progressBarComics, it.second)
                    }
                    CharacterDetailsViewModel.DetailsLoadingType.LOADING_EVENTS -> {
                        showHideProgressView(_binding.progressBarEvents, it.second)
                    }
                    else -> {

                    }
                }
            }
        }

    }

    private fun <T> displayItemLogic(items: List<T>?, recyclerView: RecyclerView, textViewEmpty: TextView, adapter: CharacterDetailsBaseAdapter<T, *>?) {
        if(items != null) {
            if(items.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                textViewEmpty.visibility = View.GONE
                adapter?.setItems(items)
            }else{
                recyclerView.visibility = View.GONE
                textViewEmpty.visibility = View.VISIBLE
            }
        }
    }

    private fun showHideProgressView(progressBar: ProgressBar, show: Boolean) {
        if(show) {
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }

    private fun setupViews() {
        _binding.recyclerViewComics.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        _comicsAdapter = ComicsAdapter()
        _binding.recyclerViewComics.adapter = _comicsAdapter

        _binding.recyclerViewEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        _eventsAdapter = EventsAdapter()
        _binding.recyclerViewEvents.adapter = _eventsAdapter

        _binding.recyclerViewStories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        _storiesAdapter = StoriesAdapter()
        _binding.recyclerViewStories.adapter = _storiesAdapter

        _binding.recyclerViewSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        _seriesAdapter = SeriesAdapter()
        _binding.recyclerViewSeries.adapter = _seriesAdapter
    }

}