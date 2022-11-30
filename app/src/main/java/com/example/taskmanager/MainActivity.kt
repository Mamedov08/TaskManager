package com.example.taskmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isBoardingShow()) {
            navController.navigate(R.id.onBoardingFragment)
        }


        // If user seen -> navigate to homeFragment
        // else navigate to onBoarding

        navController.navigate(R.id.onBoardingFragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val bottomFragments = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.taskFragment,
            R.id.navigation_profile

        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (Firebase.auth.currentUser == null){
            navController.navigate(R.id.authFragment)
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomFragments.contains(destination.id)

            if (destination.id == R.id.onBoardingFragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }

    private fun setupActionBarWithNavController(
        navController: NavController,
        appBarConfiguration: appBarConfiguration
    ) {


    }
}