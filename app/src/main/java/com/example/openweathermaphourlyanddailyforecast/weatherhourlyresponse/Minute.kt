package com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse


import com.google.gson.annotations.SerializedName

data class Minute(
    @SerializedName("dt")
    var dt: Double?,
    @SerializedName("precipitation")
    var precipitation: Int?
)