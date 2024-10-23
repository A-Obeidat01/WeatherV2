package com.weather.screens
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.weather.R
import com.weather.utility.getCurrentDateInArabic
@Composable
fun WeatherRow(city: String, temp: Double, iconResId: Int, modifier: Modifier = Modifier) {
    val date = getCurrentDateInArabic()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "$temp°C",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = city,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,  // Use the dynamically generated Arabic date here
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Display weather icon on the right using painterResource
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Gray
            )

        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun PreviewWeatherRow() {
    WeatherRow(
        city = "Dubai, AE",
        temp = 31.77,
        iconResId = R.drawable.ic_sun
    )
}*/
