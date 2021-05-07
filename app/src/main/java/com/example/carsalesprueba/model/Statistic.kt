package com.example.carsalesprueba.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Statistic(
    val deaths: Int,
    val confirmed: Int,
) : Parcelable

data class AllData(
    val data: Statistic
)
