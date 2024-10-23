package com.weather.screens



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.data.WetherModel
import com.weather.weatherItem.weatherApi.WeatherApiService
import com.weather.weatherItem.weatherApi.WeatherResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val weatherApi: WeatherApiService) : ViewModel() {

    private val _weatherData = MutableLiveData<List<WetherModel>>()
    val weatherData: LiveData<List<WetherModel>> get() = _weatherData

    fun fetchWeatherData(cityName: String) {
        viewModelScope.launch {
            weatherApi.getCityWeather(cityName, "0c5367af255b5f57540d899497fc1764").enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let { weatherResponse ->
                            val weatherModel = WetherModel(
                                city = weatherResponse.name,
                                temperature = weatherResponse.main.temp.toDouble(),
                                date = "تاريخ اليوم", // يمكنك استخدام التاريخ الفعلي هنا
                                weatherIcon = weatherResponse.main.temp > 25 // يمكنك تخصيص ذلك بناءً على البيانات
                            )
                            _weatherData.postValue(listOf(weatherModel)) // يمكنك تعديل ذلك لتحديث قائمة أكبر من المدن
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    // التعامل مع الأخطاء
                }
            })
        }
    }
}
