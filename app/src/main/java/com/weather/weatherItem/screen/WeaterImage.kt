package com.weather.weatherItem.screen

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

@Composable
fun ShowWeatherIcon(weatherIcon: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter // محاذاة الصورة إلى أسفل الشاشة
    ) {
        Image(
            painter = if (weatherIcon) {
                painterResource(R.drawable.a2) // يعرض a2.png إذا كانت weatherIcon == true
            } else {
                painterResource(R.drawable.a1) // يعرض a1.png إذا كانت weatherIcon == false
            },
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
    ShowWeatherIcon(weatherIcon = true) // جرب true لعرض a2.png
    // ShowWeatherIcon(weatherIcon = false) // جرب false لعرض a1.png
}