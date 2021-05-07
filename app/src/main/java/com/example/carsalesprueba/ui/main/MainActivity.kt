package com.example.carsalesprueba.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.carsalesprueba.R
import com.example.carsalesprueba.databinding.ActivityMainBinding
import com.example.carsalesprueba.model.Statistic
import com.example.carsalesprueba.util.DatePickerFragment
import com.example.carsalesprueba.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.Theme_CarsalesPrueba)

        setupBeginningDate()
        setupObserver()
        setupListener()
    }

    private fun setupBeginningDate() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val minusOneDay = calendar.time
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = formatter.format(minusOneDay)
        showCurrentDate(minusOneDay)
        viewModel.setDate(date)
    }

    private fun showCurrentDate(minusOneDay: Date) {
        val day = SimpleDateFormat("dd", Locale.US).format(minusOneDay)
        val month = SimpleDateFormat("MMM", Locale.US).format(minusOneDay)
        val year = SimpleDateFormat("yyyy", Locale.US).format(minusOneDay)
        updateDateInView(day, month, year)
    }

    @SuppressLint("SetTextI18n")
    private fun updateDateInView(day: String, month: String, year: String) {
        binding.date.text = "$day de $month $year"
    }

    private fun setupListener() {
        binding.btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        searchNewDate(month, day, year)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        val date = calendar.time
        val nameMonth = SimpleDateFormat("MMM", Locale.US).format(date)
        updateDateInView(day.toString(), nameMonth, year.toString())
    }

    private fun searchNewDate(month: Int, day: Int, year: Int) {
        val dateFormat = DecimalFormat("00")
        val formattedMonth: String = dateFormat.format(month + 1)
        val formattedDay: String = dateFormat.format(day)
        val date = "$year-$formattedMonth-$formattedDay"
        viewModel.setDate(date)
    }

    private fun setupObserver() {
        viewModel.getStatistic.observe(this as LifecycleOwner, this::render)
    }

    private fun render(result: Resource<Statistic>) {
        when (result) {
            is Resource.Loading -> {
                showOnlyProgressBar()
            }
            is Resource.Success -> {
                restoredView()
                putStatistic(result.data)
            }
            is Resource.Failure -> {
                restoredView()
                Toast.makeText(
                    this,
                    "Ocurrio el siguiente error: ${result.exception}",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    private fun putStatistic(data: Statistic) {
        val decimalFormat = DecimalFormat("#,###")
        binding.caseNumber.text = decimalFormat.format(data.confirmed).toString()
        binding.decesedNumber.text = decimalFormat.format(data.deaths).toString()
    }

    private fun restoredView() {
        with(binding) {
            titleConfirmedCases.visibility = View.VISIBLE
            titleNumberOfDeceasedPeople.visibility = View.VISIBLE
            date.visibility = View.VISIBLE
            caseNumber.visibility = View.VISIBLE
            decesedNumber.visibility = View.VISIBLE
            image.visibility = View.VISIBLE
            btnSelectDate.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun showOnlyProgressBar() {
        with(binding) {
            titleConfirmedCases.visibility = View.GONE
            titleNumberOfDeceasedPeople.visibility = View.GONE
            date.visibility = View.GONE
            caseNumber.visibility = View.GONE
            decesedNumber.visibility = View.GONE
            image.visibility = View.GONE
            btnSelectDate.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }
}
