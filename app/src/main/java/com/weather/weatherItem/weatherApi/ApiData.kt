package com.weather.weatherItem.weatherApi

data class WeatherResponse(
    val main: Main,
    val name: String  // هذا الحقل للمدينة من API
)

data class Main(
    val temp: Float
)