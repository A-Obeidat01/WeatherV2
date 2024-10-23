package com.weather.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun RecentCityWeather(wetherModel: WeatherModel) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        // درجة الحرارة على اليسار
        Column(horizontalAlignment = Alignment.Start) {
            // عرض درجة الحرارة ورمز الدرجة المئوية بجانب بعضهما البعض
            Row(verticalAlignment = Alignment.Top) {

                // عرض رمز الدرجة المئوية بجانب درجة الحرارة
                Text(
                    text = "°C",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp) // يمكنك تعديل حجم الخط حسب الحاجة
                )
                // عرض درجة الحرارة مع إضافة تأثير الظل
                Text(
                    text = "${wetherModel.temperature}",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.width(30.dp))

        // اسم المدينة والتاريخ
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
            Text(text = wetherModel.city, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = wetherModel.date,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // أيقونة الطقس
        /*Icon(

            imageVector = wetherModel.weatherIcon,
            contentDescription = "Weather Icon",
            modifier = Modifier.size(40.dp)
        )*/
        Icon(
            imageVector =wetherModel.status.getWeatherIcon(), // عرض أيقونة الشمس أو الغيمة بناءً على الطقس
            contentDescription = wetherModel.status.getWeatherTitle(), // وصف الأيقونة
            tint = wetherModel.status.getWeatherColor(), // تغيير اللون بناءً على الطقس
            modifier = Modifier.size(40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCityWeatherItem() {
    val weatherDataList = listOf(
        WeatherModel(
            city = "Dubai, AE",
            temperature = 31.77,
            date = "الأحد, 20 تشرين",
            status = WeatherStatus.Rainy
        ),
        WeatherModel(
            city = "Irbid, JO",
            temperature = 25.77,
            date = "الأحد, 20 تشرين",
            status = WeatherStatus.Sunny
        ),


        )
    RecentCityWeather(wetherModel = weatherDataList[1])
}
