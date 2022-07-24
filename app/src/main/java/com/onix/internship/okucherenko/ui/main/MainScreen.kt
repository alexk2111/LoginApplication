package com.onix.internship.okucherenko.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseActivity
import com.onix.internship.okucherenko.data.repository.PreferenceStorage
import com.onix.internship.okucherenko.data.repository.entity.CurrentSetting
import com.onix.internship.okucherenko.data.repository.entity.Settings
import com.onix.internship.okucherenko.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        navHostFragment.navController
    }

    lateinit var navView: BottomNavigationView

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun setObservers() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mapFragment, R.id.pointsFragment, R.id.settingsFragment
            )
        )
        navView = binding.navView
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val storage = PreferenceStorage(this)
        val value = storage.getString(getString(R.string.saved_high_score_key))
        if (value != "") {
            Settings.currentSetting = Gson().fromJson(value, CurrentSetting::class.java)
            navView.visibility = View.VISIBLE
            val navGraph = navController.navInflater.inflate(R.navigation.home)
            navGraph.setStartDestination(R.id.mapFragment) //= R.id.mapFragment
            navController.graph = navGraph
        }
    }
}