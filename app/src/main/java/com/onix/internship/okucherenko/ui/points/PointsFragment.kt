package com.onix.internship.okucherenko.ui.points

import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.data.repository.entity.Point
import com.onix.internship.okucherenko.databinding.PointsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointsFragment : BaseFragment<PointsFragmentBinding>(R.layout.points_fragment) {
    override val viewModel: PointsViewModel by viewModel()

    private val adapter = PointAdapter()

    override fun setObservers() {
        super.setObservers()

        viewModel.points.observe(viewLifecycleOwner) {
            adapter.data = it
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val clipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            var pasteData = ""

            when {
                !clipboard.hasPrimaryClip() -> {
                    Toast.makeText(requireContext(),getString(R.string.clip_board_error),Toast.LENGTH_SHORT).show()
                }
                !(clipboard.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN))!! -> {
                    Toast.makeText(requireContext(),getString(R.string.clip_board_error),Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val item = clipboard.primaryClip?.getItemAt(0)
                    pasteData = item?.text.toString()
                }
            }

            if (pasteData == "") {
                Toast.makeText(requireContext(),getString(R.string.empty),Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val point: Point = Gson().fromJson(pasteData, Point::class.java)
                    viewModel.addPoint(point)
                } catch (e: Exception) {
                Toast.makeText(requireContext(),getString(R.string.bad_data),Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.pointsList.adapter = adapter

    }
}