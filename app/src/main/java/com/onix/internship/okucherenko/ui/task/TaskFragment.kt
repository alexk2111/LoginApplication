package com.onix.internship.okucherenko.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.TaskFragmentBinding
import com.onix.internship.okucherenko.ui.dialogs.DateDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TaskFragment : BaseFragment<TaskFragmentBinding>(R.layout.task_fragment) {
    override val viewModel: TaskViewModel by viewModel()

    private val adapter = WeekAdapter(WeekListener {
        val calendar = Calendar.getInstance()
        calendar.set(it.weekDay.year, it.weekDay.month - 1, it.weekDay.day)
        viewModel.getWeek(calendar)
    })

    override fun setObservers() {
        super.setObservers()
        binding.taskViewModel = viewModel
        viewModel.showDateDialog.observe(viewLifecycleOwner) {
            if (it){
                DateDialog().show(childFragmentManager, DateDialog.TAG)
            }
        }

        viewModel.weekArr.observe(viewLifecycleOwner){
            adapter.data = it
        }

        viewModel.showToday.observe(viewLifecycleOwner){
            if (it) binding.textViewToday.visibility = View.VISIBLE
            else binding.textViewToday.visibility = View.INVISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.weekDaysName = resources.getStringArray(R.array.week_days_name)
        viewModel.getWeek(Calendar.getInstance())

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewWeek.adapter = adapter

    }



}