package com.example.consumoapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.consumoapirest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //llamado
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Configura el contenido de la actividad

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_pb) as NavHostFragment
        val navController = navHostFragment.navController

        binding.btnMenu.setupWithNavController(navController)

        binding.btnMenu.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.listadoFragment -> {
                    navController.navigate(R.id.listadoFragment)
                    true
                }
                R.id.favoritoFragment -> {
                    navController.navigate(R.id.favoritoFragment)
                    true
                }
                R.id.firebaseFragment -> {
                    navController.navigate(R.id.firebaseFragment)
                    true
                }
                R.id.logoutFragment -> {
                    navController.navigate(R.id.logoutFragment)
                    true
                }

            }

        }

    }
}