package com.example.carsalesprueba.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Statistic(
    val recovered: Int,
    val deaths: Int,
    val confirmed: Int,
    val lastChecked: String,
    val lastReported: String,
    val location: String
) : Parcelable

data class listStatistics(
    @SerializedName("data")
    val data: List<Statistic>
)