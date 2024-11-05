package com.weather.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import com.weather.data.WeatherDataResponse
import com.weather.utility.RetrofitInstance
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchBar(
    onSearchQueryChanged: (String) -> Unit = {}
) {
    // State for the search query input
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var weatherState by remember { mutableStateOf<WeatherDataResponse?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEAEAF1))
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onSearchQueryChanged(it.text)

                        // Trigger API call when user types
                        coroutineScope.launch {
                            if (searchQuery.text.isNotEmpty()) {
                                try {
                                    // Call the API using Retrofit instance
                                    val response = RetrofitInstance.api.getWeather(
                                        city = searchQuery.text,
                                    )
                                    weatherState = response
                                    errorMessage = null
                                } catch (e: Exception) {
                                    weatherState = null
                                    errorMessage = "Could not fetch weather"
                                }
                            }
                        }
                    },
                    placeholder = { Text("Search City Weather") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor  = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                    ),
                    singleLine = true
                )
            }

            // Display weather or error message
            weatherState?.let {
                Text(
                    text = "Temperature in ${it.name}: ${it.main.temp}°C, Humidity: ${it.main.humidity}%",
                    modifier = Modifier.padding(16.dp)
                )
            } ?: errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWeatherSearchBar() {
    WeatherSearchBar()
}

