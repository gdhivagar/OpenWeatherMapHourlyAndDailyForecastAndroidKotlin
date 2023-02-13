package com.example.openweathermaphourlyanddailyforecast.network

import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_APP_ID
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_EXCLUDE
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_LATITUDE
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_LONGITUDE
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_ONE_CALL
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_UNITS
import com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse.HourlyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET(KEY_ONE_CALL)
    fun getAllWeatherReports(
        @Query(KEY_UNITS) units: String,
        @Query(KEY_EXCLUDE) exclude: String,
        @Query(KEY_APP_ID) appid: String,
        @Query(KEY_LATITUDE) lat: String,
        @Query(KEY_LONGITUDE) lon: String,
    ): Call<HourlyResponse>

}