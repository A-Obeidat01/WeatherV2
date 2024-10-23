package com.weather

import SearchBar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weather.ui.theme.WeatherV2Theme
import com.weather.data.WetherModel
import com.weather.screens.CityWeatherItem
import com.weather.weatherItem.weatherApi.WeatherApiService
import com.weather.weatherItem.weatherApi.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enables edge-to-edge rendering on supported devices
        setContent {
            WeatherV2Theme {
                WeatherScreen() // تعديل لاستدعاء شاشة الطقس
                ShowWeatherIcon(weatherIcon = true)
            }
        }
    }
}

@Composable
fun WeatherScreen() {
    // بيانات الطقس التي سيتم عرضها
    val weatherDataList = listOf(
        WetherModel(
            city = "Dubai, AE",
            temperature = 31.77,
            date = "الأحد, 20 تشرين",
            weatherIcon = false
        ),
        WetherModel(
            city = "Amman, JO",
            temperature = 25.0,
            date = "الأحد, 20 تشرين",
            weatherIcon = true
        ),
        WetherModel(
            city = "Cairo, EG",
            temperature = 30.5,
            date = "الأحد, 20 تشرين",
            weatherIcon = true
        )
    )

    // عرض الـ Pager الذي يسمح بالتنقل بين بيانات الطقس
    WeatherPager(weatherDataList = weatherDataList)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeatherPager(weatherDataList: List<WetherModel>) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = weatherDataList.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) { page ->
        val weatherData = weatherDataList[page]
        CityWeatherItem(wetherModel = weatherData)  // تعديل هنا لتمرير نموذج كامل
    }
}
*/
/*
class MainActivity : ComponentActivity() {
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enables edge-to-edge rendering on supported devices

        // إعداد Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApiService::class.java)

        // إعداد ViewModel
        weatherViewModel = ViewModelProvider(this, ViewModelFactory(weatherApi)).get(WeatherViewModel::class.java)

        setContent {
            WeatherV2Theme {
                WeatherScreen(weatherViewModel)
            }
        }
    }
}

*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enables edge-to-edge rendering on supported devices
        val currentDateTime = LocalDateTime.now()
        val formattedDate = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        setContent {
            WeatherV2Theme {
                WeatherScreen()
            }
        }
    }

    @Composable
    fun WeatherScreen() {
        var weatherData by remember { mutableStateOf<WetherModel?>(null) }
        var searchQuery by remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            SearchBar(searchQuery) { newText ->
                searchQuery = newText
                if (newText.isNotEmpty()) {
                    fetchWeather(newText) { fetchedWeather ->
                        weatherData = fetchedWeather
                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            weatherData?.let {
                CityWeatherItem(wetherModel = it)
            } ?: run {
                Text(text = "Please enter a city to get weather information.")
            }
        }
    }

    private fun fetchWeather(cityName: String, onWeatherFetched: (WetherModel?) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApiService::class.java)
        val apiKey = "0c5367af255b5f57540d899497fc1764" // استبدل بـ API Key الخاص بك

        weatherApi.getCityWeather(cityName, apiKey, "metric").enqueue(object :
            Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherResponse ->
                        val temperature = weatherResponse.main.temp
                        val wetherModel = WetherModel(
                            city = weatherResponse.name,
                            temperature = temperature.toDouble(),
                            date = "Current Date", // يمكنك تحديث التاريخ كما تحتاج
                            weatherIcon = temperature >= 20 // مثال على استخدام درجة الحرارة لتحديد أيقونة الطقس
                        )
                        onWeatherFetched(wetherModel)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}