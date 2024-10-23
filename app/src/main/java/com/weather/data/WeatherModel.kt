package com.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class WeatherModel(
    val city: String,
    val temperature: Double,
    val date: String,
    val status: WeatherStatus
) : Parcelable

enum class WeatherStatus{
    Sunny,Rainy
}