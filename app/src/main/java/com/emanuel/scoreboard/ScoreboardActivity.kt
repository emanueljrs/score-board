package com.emanuel.scoreboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emanuel.scoreboard.databinding.ActivityMainBinding

class ScoreboardActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHost = supportFragmentManager
            .findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHost.navController
        val appBarConfig = AppBarConfiguration(navGraph = navController.graph)

        binding.mainToolbar.setupWithNavController(
            navController = navController,
            configuration = appBarConfig
        )
    }
}