package com.tonimurr.marvel.presentation.screen_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.tonimurr.marvel.R
import com.tonimurr.marvel.databinding.FragmentHomeBinding
import com.tonimurr.marvel.domain.model.MarvelCharacter
import com.tonimurr.marvel.presentation.base.MarvelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : MarvelFragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val _viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MarvelCharactersAdapter()
        _binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        _binding.recyclerViewCharacters.adapter = adapter

        adapter.setMarvelCharactersAdapterInterface(object: MarvelCharactersAdapter.MarvelCharactersAdapterInterface {
            override fun triggerLoadMore() {
                _viewModel.fetchCharacters()
            }

            override fun didClickOnCharacter(view: View, character: MarvelCharacter) {
                val action = HomeFragmentDirections.actionHomeFragmentToCharacterDetailsFragment(character)
                Navigation.findNavController(view).navigate(action)
            }
        })

        _viewModel.liveMarvelCharacters().observe(viewLifecycleOwner) { marvelCharacters ->
            marvelCharacters?.let {
                adapter.setMarvelCharacters(it)
            }
        }

        _viewModel.liveProgressBar().observe(viewLifecycleOwner) { show ->
            if(show) {
                _binding.progressBar.visibility = View.VISIBLE
            }else{
                _binding.progressBar.visibility = View.GONE
            }
        }

    }

}