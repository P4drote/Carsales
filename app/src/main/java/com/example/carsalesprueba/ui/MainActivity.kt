package com.example.carsalesprueba.ui

import android.os.Bundle
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.Theme_CarsalesPrueba)

        setupHour()
        setupObserver()
        setupListener()
    }

    private fun setupHour() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US)
        val date = formatter.format(Date())
        viewModel.setDate(date)
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
        //date
    }

    private fun setupObserver() {
        viewModel.getStatistic.observe(this as LifecycleOwner, this::render)
    }

    private fun render(result: Resource<List<Statistic>>) {
        when(result) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
            }
            is Resource.Failure -> {
                Toast.makeText(this, "Ocurrio un error: ${result.exception}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}