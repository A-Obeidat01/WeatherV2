package com.weather.data

class WeatherDataMapper {
    fun map(weatherDataEntity: WeatherDataEntity): WeatherData {
        return WeatherData(
            coord = mapCoord(weatherDataEntity.coord),
            weather = weatherDataEntity.weather.map { mapWeather(it) },
            base = weatherDataEntity.base,
            main = mapMain(weatherDataEntity.main),
            visibility = weatherDataEntity.visibility,
            wind = mapWind(weatherDataEntity.wind),
            clouds = mapClouds(weatherDataEntity.clouds),
            dt = weatherDataEntity.dt,
            sys = mapSys(weatherDataEntity.sys),
            timezone = weatherDataEntity.timezone,
            id = weatherDataEntity.id,
            name = weatherDataEntity.name,
            cod = weatherDataEntity.cod
        )
    }

    private fun mapCoord(coordEntity: CoordEntity) = Coord(
        lon = coordEntity.lon,
        lat = coordEntity.lat
    )

    private fun mapWeather(weatherEntity: WeatherEntity) = Weather(
        id = weatherEntity.id,
        main = weatherEntity.main,
        description = weatherEntity.description,
        icon = weatherEntity.icon
    )

    private fun mapMain(mainEntity: MainEntity) = Main(
        temp = mainEntity.temp,
        feelsLike = mainEntity.feelsLike,
        tempMin = mainEntity.tempMin,
        tempMax = mainEntity.tempMax,
        pressure = mainEntity.pressure,
        humidity = mainEntity.humidity,
        seaLevel = mainEntity.seaLevel,
        grndLevel = mainEntity.grndLevel,
        status = mapWeatherStatus(mainEntity.temp)
    )

    private fun mapWeatherStatus(temp: Double): WeatherStatus {
        return when {
            temp < 0 -> WeatherStatus.COLD
            temp in 0.0..15.0 -> WeatherStatus.COOL
            temp in 15.0..25.0 -> WeatherStatus.WARM
            temp > 25 -> WeatherStatus.HOT
            else -> WeatherStatus.UNKNOWN
        }

    }

    private fun mapWind(windEntity: WindEntity) = Wind(
        speed = windEntity.speed,
        deg = windEntity.deg,
        gust = windEntity.gust
    )

    private fun mapClouds(cloudsEntity: CloudsEntity) = Clouds(
        all = cloudsEntity.all
    )

    private fun mapSys(sysEntity: SysEntity) = Sys(
        country = sysEntity.country,
        sunrise = sysEntity.sunrise,
        sunset = sysEntity.sunset
    )
}