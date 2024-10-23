package com.weather.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.weather.utility.formatDate
import java.time.LocalDate

@Composable
fun TodayWeather() {
    val currentDate = LocalDate.now().formatDate()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Today",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = currentDate,  // Use the dynamic date here
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(0xFFFF7043), shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "21.5°C",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Irbid Governorate, JO",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTodayWeather() {
    TodayWeather()
}