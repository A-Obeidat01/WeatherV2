package com.weather.weatherItem.weatherApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getCityWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"  // لعرض درجات الحرارة بالدرجة المئوية
    ): Call<WeatherResponse>
}