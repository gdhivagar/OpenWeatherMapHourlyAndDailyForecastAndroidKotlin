package com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("main")
    var main: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("icon")
    var icon: String?
)