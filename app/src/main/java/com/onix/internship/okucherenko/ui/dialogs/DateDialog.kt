package com.onix.internship.okucherenko.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.onix.internship.okucherenko.data.repository.entity.MyDate

class DateDialog: DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
//        val year = actualDate.get(Calendar.YEAR)
//        val month = actualDate.get(Calendar.MONTH)
//        val day = actualDate.get(Calendar.DAY_OF_MONTH)
        val year = actualDate.year
        val month = actualDate.month
        val day = actualDate.day

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    }

    companion object {
        const val TAG = "DateDialog"
        lateinit var actualDate: MyDate

    }
}