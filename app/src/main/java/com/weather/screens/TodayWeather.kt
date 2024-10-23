package com.weather.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.data.WeatherModel
import com.weather.data.WeatherStatus
import com.weather.utility.getWeatherColor
import com.weather.utility.getWeatherIcon
import com.weather.utility.getWeatherTitle

@Composable
fun TodayDateView(
    wetherModel: WeatherModel
) {
    // Main layout for the weather info
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Row for "Today", date, and sun
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End // Align elements to the start (left side)
        ) {
            // Column for "Today" and date
            Column(
                horizontalAlignment = Alignment.End // Align text to the right
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = wetherModel.date,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.width(25.dp)) // Space between text and circle

            // أيقونة الطقس
            /*Icon(
                imageVector = wetherModel.weatherIcon,
                contentDescription = "Weather Icon",
                modifier = Modifier.size(40.dp)
            )*/
            Icon(
                imageVector =wetherModel.status.getWeatherIcon(), // عرض أيقونة الشمس أو الغيمة بناءً على الطقس
                contentDescription = wetherModel.status.getWeatherTitle(), // وصف الأيقونة
                tint = wetherModel.status.getWeatherColor(),
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Temperature display
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "℃",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(end = 2.dp)
            )
            Text(
                text = "${wetherModel.temperature}",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 80.sp
                )

            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Location

        Text(
            text = wetherModel.city,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 25.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherScreen() {
    val weatherData = WeatherModel(
        city = "Irbid Governorate, JO",
        temperature = 21.5,
        date = "الاثنين، 21 تشرين الأول 2024",
        status = WeatherStatus.Rainy
    )
    TodayDateView(wetherModel = weatherData) // استدعاء مع بيانات الطقس
}
