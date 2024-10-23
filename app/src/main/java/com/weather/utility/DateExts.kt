package com.weather.utility

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getCurrentDateInArabic(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale("ar"))
    return currentDate.format(formatter)
}

fun LocalDate.formatDate(formatter: String = "EEEE, d MMMM yyyy"): String {
    return this.format(DateTimeFormatter.ofPattern(formatter, Locale.getDefault())).orEmpty()
}

