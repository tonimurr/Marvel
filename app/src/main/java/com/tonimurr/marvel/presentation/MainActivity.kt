package com.tonimurr.marvel.presentation

import android.os.Bundle
import com.tonimurr.marvel.databinding.ActivityMainBinding
import com.tonimurr.marvel.presentation.base.MarvelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : MarvelActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}