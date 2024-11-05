package com.weather.data

import com.google.gson.annotations.SerializedName

data class CoordEntity(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class WeatherEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class MainEntity(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val grndLevel: Int
)

data class WindEntity(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double
)

data class CloudsEntity(
    @SerializedName("all") val all: Int
)

data class SysEntity(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class WeatherDataEntity(
    @SerializedName("coord") val coord: CoordEntity,
    @SerializedName("weather") val weather: List<WeatherEntity>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: MainEntity,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: WindEntity,
    @SerializedName("clouds") val clouds: CloudsEntity,
    @SerializedName("dt") val dt: Long,
    @SerializedName("sys") val sys: SysEntity,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)
