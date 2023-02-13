package com.example.openweathermaphourlyanddailyforecast

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.openweathermaphourlyanddailyforecast.adapter.DailyWeatherAdapter
import com.example.openweathermaphourlyanddailyforecast.adapter.HourlyWeatherAdapter
import com.example.openweathermaphourlyanddailyforecast.network.ServiceGenerator
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.API_KEY
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.CODE_SUCCESS
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_METRIC
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_MINUTELY
import com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse.Daily
import com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse.Hourly
import com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse.HourlyResponse
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var dailyWeatherAdapter: DailyWeatherAdapter
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter

    private val dailyTempListMin = ArrayList<Daily>()
    private val dailyTempListMax = ArrayList<Daily>()
    private var defaultLatitude: String? = "25.2048"
    private var defaultLongitude: String? = "55.2708"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val geocoder = Geocoder(applicationContext, Locale.getDefault())
        val latitude = defaultLatitude!!
        val longitude = defaultLongitude!!
        val list: List<Address> =
            geocoder.getFromLocation(
                latitude.toDouble(),
                longitude.toDouble(), 1
            )
        tvCityName.text = list[0].locality


        getWeatherDetails(latitude, longitude)

    }

    /*GET openweathermap API code starts here*/
    private fun getWeatherDetails(latitude: String, longitude: String) {

        val call: Call<HourlyResponse> = ServiceGenerator.getClient.getAllWeatherReports(
            KEY_METRIC, KEY_MINUTELY, API_KEY, latitude, longitude
        )

        call.enqueue(object : Callback<HourlyResponse> {
            override fun onResponse(
                call: Call<HourlyResponse>,
                response: Response<HourlyResponse>,
            ) {
                if (response.code() == CODE_SUCCESS) {
                    setDailyWeather(response.body()?.daily)
                    setHourlyWeather(response.body()?.hourly)
                }
            }

            override fun onFailure(call: Call<HourlyResponse>, t: Throwable) {
                try {
                    Toast.makeText(
                        applicationContext,
                        "Please try again later !!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
    /*GET openweathermap API code ends here*/

    private fun setDailyWeather(daily: List<Daily>?) {
        val dailyList = ArrayList<Daily>()
        dailyList.addAll(daily!!)

        val layoutManager =
            GridLayoutManager(this@MainActivity, 1, GridLayoutManager.HORIZONTAL, false)

        dailyWeatherAdapter = DailyWeatherAdapter(this)
        dailyWeatherRecycler.layoutManager = layoutManager
        dailyWeatherRecycler.adapter = dailyWeatherAdapter
        dailyWeatherAdapter.setData(dailyList)

        /*Line chart code starts*/
        lineChart.xAxis.isEnabled = false

        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.isEnabled = false

        lineChart.legend.isEnabled = false

        lineChart.description.isEnabled = false

        val entriesMin: ArrayList<Entry> = ArrayList()
        val entriesMax: ArrayList<Entry> = ArrayList()

        daily.let {
            dailyTempListMin.addAll(it)
        }

        for (i in dailyTempListMin.indices) {
            val minReport = dailyTempListMin[i]
            entriesMin.add(Entry(i.toFloat(), minReport.temp?.min!!.toFloat()))
        }

        daily.let {
            dailyTempListMax.addAll(it)
        }

        for (i in dailyTempListMax.indices) {
            val maxReport = dailyTempListMax[i]
            entriesMax.add(Entry(i.toFloat(), maxReport.temp?.max!!.toFloat()))
        }

        val lineDataSet = LineDataSet(entriesMin, "Temperature")
        val colorOne: ArrayList<Int> = ArrayList()
        colorOne.add(Color.parseColor("#FF632FF2"))
        lineDataSet.colors = colorOne
        lineDataSet.valueTextColor = resources.getColor(R.color.color_purple_400, null)
        lineDataSet.valueTextSize = 12F
        lineDataSet.setDrawCircles(true)
        lineDataSet.setCircleColor(resources.getColor(R.color.color_purple_400, null))
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.disableDashedLine()

        val lineDataSets = LineDataSet(entriesMax, "Humidity")
        val colorTwo: ArrayList<Int> = ArrayList()
        colorTwo.add(Color.parseColor("#FF632FF2"))
        lineDataSets.colors = colorTwo
        lineDataSets.valueTextColor = resources.getColor(R.color.color_purple_400, null)
        lineDataSets.valueTextSize = 12F
        lineDataSets.setDrawCircles(true)
        lineDataSets.setCircleColor(resources.getColor(R.color.color_purple_400, null))
        lineDataSets.setDrawCircleHole(false)
        lineDataSets.disableDashedLine()

        val dataOne = ArrayList<LineDataSet>()

        dataOne.add(lineDataSet)
        dataOne.add(lineDataSets)
        val dataTwo = LineData(dataOne as List<ILineDataSet>?)
        lineChart.data = dataTwo
        dataTwo.setDrawValues(true)
        lineChart.isAutoScaleMinMaxEnabled = true
        lineChart.notifyDataSetChanged()
        lineChart.invalidate()
        /*Line chart code ends*/
    }

    private fun setHourlyWeather(hourly: List<Hourly>?) {
        val hourlyList = ArrayList<Hourly>()
        hourlyList.addAll(hourly!!)

        val hourlyLayoutManager =
            GridLayoutManager(this@MainActivity, 1, GridLayoutManager.HORIZONTAL, false)

        hourlyWeatherAdapter = HourlyWeatherAdapter(this)
        hourlyWeatherRecycler.layoutManager = hourlyLayoutManager
        hourlyWeatherRecycler.adapter = hourlyWeatherAdapter
        hourlyWeatherAdapter.setData(hourlyList)
    }

}