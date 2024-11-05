package com.weather.data

import com.weather.data.WeatherDataResponse
import com.weather.utility.AppConstant
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String= AppConstant.APP_ID,
        @Query("units") units: String = "metric"
    ): WeatherDataResponse
}
