package com.onix.internship.okucherenko.ui.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.data.repository.entity.Settings
import com.onix.internship.okucherenko.databinding.MapFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<MapFragmentBinding>(R.layout.map_fragment) {
    override val viewModel: MapViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLocationPermission()

        binding.button.setOnClickListener {
            shareSuccess()
        }
    }

    private fun getShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, Gson().toJson(Settings.currentSetting))
        return Intent.createChooser(shareIntent, Gson().toJson(Settings.currentSetting))
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    private fun checkLocationPermission() {
    }
}
