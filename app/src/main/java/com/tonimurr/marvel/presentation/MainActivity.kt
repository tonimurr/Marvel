package com.tonimurr.marvel.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.tonimurr.marvel.R
import com.tonimurr.marvel.databinding.ActivityMainBinding
import com.tonimurr.marvel.presentation.base.MarvelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : MarvelActivity() {

    private lateinit var _binding: ActivityMainBinding
    private var _navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setSupportActionBar(_binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        _navController = navHostFragment.navController
        _navController?.let {
            setupActionBarWithNavController(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                _navController?.navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}