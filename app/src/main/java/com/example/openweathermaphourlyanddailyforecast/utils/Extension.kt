package com.example.openweathermaphourlyanddailyforecast.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateFormatWeather {
    @SuppressLint("SimpleDateFormat")
    fun getDateTime(dt: String, regex: String): String? {
        return try {
            val dateFormat = SimpleDateFormat(regex)
            val dateParse = Date(dt.toLong() * 1000)

            dateFormat.format(dateParse)
        } catch (e: Exception) {
            e.message
        }
    }
}

