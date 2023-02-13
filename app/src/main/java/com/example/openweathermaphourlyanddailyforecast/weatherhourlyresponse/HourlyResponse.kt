package com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse

import com.google.gson.annotations.SerializedName

data class HourlyResponse(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lon")
    var lon: Double?,
    @SerializedName("timezone")
    var timezone: String?,
    @SerializedName("timezone_offset")
    var timezoneOffset: Double?,
    @SerializedName("current")
    var current: Current?,
    @SerializedName("minutely")
    var minutley: Minute?,
    @SerializedName("daily")
    var daily: List<Daily>?,
    @SerializedName("hourly")
    var hourly: List<Hourly>?
)