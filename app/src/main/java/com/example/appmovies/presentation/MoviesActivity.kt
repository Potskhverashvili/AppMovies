package com.example.appmovies.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.appmovies.R
import com.example.appmovies.databinding.ActivityMoviesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMoviesBinding

    val user = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)




        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (user) {
            navGraph.setStartDestination(R.id.containerFragment)
        } else {
            navGraph.setStartDestination(R.id.signUpFragment)
        }
        navController.graph = navGraph*/


    }




}