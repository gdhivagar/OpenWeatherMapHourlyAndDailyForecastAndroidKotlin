package com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("dt")
    var dt: Int?,
    @SerializedName("temp")
    var temp: Double?,
    @SerializedName("feels_like")
    var feelsLike: Double?,
    @SerializedName("pressure")
    var pressure: Double?,
    @SerializedName("humidity")
    var humidity: Double?,
    @SerializedName("dew_point")
    var dewPoint: Double?,
    @SerializedName("uvi")
    var uvi: Double?,
    @SerializedName("clouds")
    var clouds: Double?,
    @SerializedName("visibility")
    var visibility: Double?,
    @SerializedName("wind_speed")
    var windSpeed: Double?,
    @SerializedName("wind_deg")
    var windDeg: Double?,
    @SerializedName("wind_gust")
    var windGust: Double?,
    @SerializedName("weather")
    var weather: List<Weather>?,
    @SerializedName("pop")
    var pop: Double?
)