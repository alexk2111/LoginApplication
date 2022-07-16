package com.onix.internship.okucherenko.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseActivity
import com.onix.internship.okucherenko.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.taskFragment, R.id.activityFragment, R.id.folderFragment
            )
        )
        val navView: BottomNavigationView = binding.navView
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.floatingActionButton.setOnClickListener {
            Toast.makeText(this, "kjdbvkjsdbvjkb", Toast.LENGTH_SHORT).show()
        }

    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun setObservers() {}

}