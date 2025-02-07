package com.example.appmovies.presentation.containerFragment

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appmovies.R
import com.example.appmovies.core.base.BaseFragment
import com.example.appmovies.databinding.FragmentContainerBinding

class ContainerFragment :
    BaseFragment<FragmentContainerBinding>(FragmentContainerBinding::inflate) {
    override fun viewCreated() {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? NavHostFragment
                ?: return

        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
    }
}