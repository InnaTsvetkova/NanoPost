package com.example.nanopost.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.nanopost.R
import com.example.nanopost.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.contracts.contract

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navHostFragment by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>()
    }

    private val navController: NavController
        get() = navHostFragment.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)


        binding.bnv.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.bnv.isVisible = destination.id !in listOf(
                R.id.authFragment,
                R.id.imageFragment,
                R.id.createPostFragment
            )
        }


    }
}