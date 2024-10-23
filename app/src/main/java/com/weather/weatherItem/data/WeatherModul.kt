package com.weather.weatherItem.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class WetherModel(
    val city: String,
    val temperature: Double,
    val date: String,
    val weatherIcon: Boolean// @RawValue ImageVector // نستخدم @RawValue هنا لأن ImageVector ليس مدعومًا من Parcel بشكل مباشر
) : Parcelable
