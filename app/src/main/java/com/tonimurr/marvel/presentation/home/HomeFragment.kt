package com.tonimurr.marvel.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tonimurr.marvel.databinding.FragmentHomeBinding
import com.tonimurr.marvel.presentation.base.MarvelFragment

class HomeFragment : MarvelFragment() {

    private lateinit var _binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

}