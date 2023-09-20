package com.tonimurr.marvel.presentation.screen_character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tonimurr.marvel.databinding.FragmentCharacterDetailsBinding
import com.tonimurr.marvel.presentation.base.MarvelFragment

class CharacterDetailsFragment : MarvelFragment() {

    private lateinit var _binding: FragmentCharacterDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater)
        return _binding.root
    }
}