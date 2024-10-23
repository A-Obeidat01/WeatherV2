package com.weather.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.R
import com.weather.data.WeatherStatus
import com.weather.utility.getWeatherImage

@Composable
fun ShowWeatherIcon(weatherStatus: WeatherStatus) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter =  painterResource(weatherStatus.getWeatherImage()),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // تعديل الارتفاع حسب الحاجة
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShowWeatherIcon() {
    // عرض صورة بناءً على قيمة weatherIcon
    ShowWeatherIcon(weatherStatus = WeatherStatus.Rainy) // جرب true لعرض a2.png
    // ShowWeatherIcon(weatherIcon = false) // جرب false لعرض a1.png
}