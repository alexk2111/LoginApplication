package com.onix.internship.okucherenko.ui.main

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun setObservers() {}

}