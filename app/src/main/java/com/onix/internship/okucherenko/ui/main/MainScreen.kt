package com.onix.internship.okucherenko.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseActivity
import com.onix.internship.okucherenko.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navView: BottomNavigationView

    override val viewModel: MainViewModel by viewModel()

    override val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun setObservers() {}

    fun setNavView(){
        navView = binding.bottomNavigation
        navView.setupWithNavController(navController)
        navView.visibility = View.VISIBLE
        val navGraph = navController.navInflater.inflate(R.navigation.tab_navigation)
        navController.graph = navGraph
    }
}