package com.example.carsalesprueba.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        picker.datePicker.minDate = setupMinDate()
        picker.datePicker.maxDate = setupMaxDate()
        return picker
    }

    private fun setupMinDate(): Long {
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, 2020)
        minDate.set(Calendar.MONTH, 2)
        minDate.set(Calendar.DAY_OF_MONTH, 1)
        return minDate.timeInMillis
    }

    private fun setupMaxDate(): Long {
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_YEAR, -1)
        return maxDate.timeInMillis
    }
}
