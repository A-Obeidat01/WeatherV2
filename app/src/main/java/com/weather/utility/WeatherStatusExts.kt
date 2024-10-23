package com.weather.utility

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.weather.R
import com.weather.data.WeatherStatus

fun WeatherStatus.getWeatherImage(): Int {
    return when (this) {
        WeatherStatus.Sunny -> R.drawable.weather_status_sunny
        WeatherStatus.Rainy -> R.drawable.weather_status_rainy
    }

}

fun WeatherStatus.getWeatherIcon(): ImageVector {
    return when (this) {
        WeatherStatus.Sunny -> Icons.Default.WbSunny
        WeatherStatus.Rainy -> Icons.Default.Cloud
    }

}
fun WeatherStatus.getWeatherTitle(): String {
    return when (this) {
        WeatherStatus.Sunny -> "Sunny"
        WeatherStatus.Rainy -> "Rainy"
    }

}
fun WeatherStatus.getWeatherColor(): Color {
    return when (this) {
        WeatherStatus.Sunny -> Color(0xFFFFA500)
        WeatherStatus.Rainy -> Color.Gray
    }

}
