package com.onix.internship.okucherenko.ui.wifimanager

import android.Manifest
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.ManagerFragmentBinding
import com.onix.internship.okucherenko.domain.entity.WiFiDot
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.min

class ManagerFragment : BaseFragment<ManagerFragmentBinding>(R.layout.manager_fragment) {
    override val viewModel: ManagerViewModel by viewModel()
    lateinit var wifiManager: WifiManager
    lateinit var currentContext: Context
    private val requestPermissionContract = ActivityResultContracts.RequestPermission()
    private lateinit var layout: ConstraintLayout
    private var viewsList = mutableListOf<View>()

    override fun setObservers() {
        super.setObservers()
        binding.managerViewModel = viewModel
        layout = binding.managerConstraintLayout
        viewModel.wifiList.observe(viewLifecycleOwner, Observer<MutableList<WiFiDot>> { newValueList ->
            updateScreen(newValueList)
        })
        viewModel.accessLocation.observe(viewLifecycleOwner, Observer<Boolean> { newAccessLocation ->
            if (newAccessLocation) startWifiManager()
        })
    }

    private fun updateScreen(newValueList: MutableList<WiFiDot>) {

        val diameter = min(layout.height, layout.width)
        val diameterCircle = (diameter * 0.1).toInt()
        val radius = diameter / 2 - diameterCircle

        viewsList.forEach { layout.removeView(it) }
        viewsList = mutableListOf()

        val angle = 360 / newValueList.size
        newValueList.forEachIndexed { index, element ->
            val set = ConstraintSet()
            val imageView = ImageView(currentContext)
            imageView.id = View.generateViewId()
            imageView.setImageResource(R.drawable.circle)
            layout.addView(imageView)
            set.constrainCircle(
                imageView.id,
                binding.imageView2.id,
                (radius) - (radius * element.level / 100) + diameterCircle / 2,
                (index * angle).toFloat()
            )
            set.applyTo(layout)
            imageView.layoutParams.width = diameterCircle
            imageView.layoutParams.height = diameterCircle
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.setOnClickListener { showDialog(element.name, element.level.toString()) }
            viewsList.add(imageView)
        }
        newValueList.forEachIndexed { index, value ->
            if (value.level < 25)
                viewsList[index].startAnimation(
                    AnimationUtils.loadAnimation(
                        currentContext,
                        R.anim.fade_out
                    )
                )
        }
    }

    private fun showDialog(name: String, level: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(currentContext)
        builder.setTitle(getString(R.string.point_info))
        builder.setIcon(R.drawable.ic_baseline_info_24)
        builder.setMessage("SSID: $name  Level: $level")
        builder.setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private val requestAccessFineLocation =
        registerForActivityResult(requestPermissionContract) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.accessFineLocation = true
                viewModel.checkAccessLocation()
            }
        }

    private val requestAccessCoarseLocation =
        registerForActivityResult(requestPermissionContract) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.accessCoarseLocation = true
                viewModel.checkAccessLocation()
            }
        }

    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentContext = requireContext()
        wifiManager = currentContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        grantPermissionAccessFineLocation(Manifest.permission.ACCESS_FINE_LOCATION)
        grantPermissionAccessCoarseLocation(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun startWifiManager() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context?.registerReceiver(wifiScanReceiver, intentFilter)

        val handler = Handler(Looper.getMainLooper())

        handler.post (object : Runnable {
            override fun run() {
            val success = wifiManager.startScan()
            handler.postDelayed(this, 3000)
        }})
    }

    private fun scanSuccess() {
        val results = wifiManager.scanResults
        viewModel.updateWifiLiveData(results)
    }

    private fun scanFailure() {
        val results = wifiManager.scanResults
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(this.binding.root, text, Snackbar.LENGTH_INDEFINITE)
            .setAction("Ok") {
            }
            .show()
    }

    private fun grantPermissionAccessFineLocation(permission: String) {
        when {
            ContextCompat.checkSelfPermission(
                currentContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.accessFineLocation = true
                viewModel.checkAccessLocation()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                showSnackbar(getString(R.string.rationale_permission))
                requestAccessFineLocation.launch(permission)
            }
            else -> {
                requestAccessFineLocation.launch(permission)
            }
        }
    }

    private fun grantPermissionAccessCoarseLocation(permission: String) {
        when {
            ContextCompat.checkSelfPermission(
                currentContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.accessCoarseLocation = true
                viewModel.checkAccessLocation()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                showSnackbar(getString(R.string.rationale_permission))
                requestAccessCoarseLocation.launch(permission)
            }
            else -> {
                requestAccessCoarseLocation.launch(permission)
            }
        }
    }
}