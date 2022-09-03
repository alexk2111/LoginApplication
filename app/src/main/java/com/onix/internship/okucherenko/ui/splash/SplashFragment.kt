package com.onix.internship.okucherenko.ui.splash

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.arch.ext.navigate
import com.onix.internship.okucherenko.databinding.SplashFragmentBinding
import com.onix.internship.okucherenko.ui.main.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashFragmentBinding>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()

    override fun setObservers() {
        viewModel.initEvent.observe(this) {
            if (it) showLogInScreen()
        }
    }

    private fun showLogInScreen() {
        val mainScreen = activity as MainScreen
        mainScreen.setNavView()
        navigate(R.id.tab_navigation, clearStack = true)
    }

}