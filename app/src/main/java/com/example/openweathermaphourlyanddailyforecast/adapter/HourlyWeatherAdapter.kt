package com.example.openweathermaphourlyanddailyforecast.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermaphourlyanddailyforecast.R
import com.example.openweathermaphourlyanddailyforecast.utils.DateFormatWeather
import com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse.Hourly
import kotlinx.android.synthetic.main.item_daily.view.*
import kotlinx.android.synthetic.main.item_hourly.view.*
import java.util.ArrayList

class HourlyWeatherAdapter(var context: Context) :
    RecyclerView.Adapter<HourlyWeatherAdapter.MyViewHolder>() {

    private var hourlyList = ArrayList<Hourly>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_hourly, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(hourlyList[position], context, position)

    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(hourly: Hourly, context: Context, position: Int) {

            val getConvertedDate = hourly.dt.let {
                DateFormatWeather.getDateTime(
                    it.toString(),
                    "HH:mm"
                )
            }

            view.tvHourlyWeatherTime.text = getConvertedDate

            val tempValue = hourly.temp.toString().substringBefore(".") + "°"
            view.tvHourlyWeatherTemp.text = tempValue

            for (j in 0 until hourly.weather?.size!!) {

                val getValue = hourly.weather!![j].icon

                when (getValue) {
                    "01d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_01d)
                    }
                    "01n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_01n)
                    }
                    "02d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_02d)
                    }
                    "02n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_02n)
                    }
                    "03d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_03d)
                    }
                    "03n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_03n)
                    }
                    "04d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_04d)
                    }
                    "04n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_04n)
                    }
                    "09d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_09d)
                    }
                    "09n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_09n)
                    }
                    "10d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_10d)
                    }
                    "10n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_10n)
                    }
                    "11d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_11d)
                    }
                    "11n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_11n)
                    }
                    "13d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_13d)
                    }
                    "13n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_13n)
                    }
                    "50d" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_50d)
                    }
                    "50n" -> {
                        view.ivHourlyWeatherIcon.setBackgroundResource(R.drawable.ic_50n)
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(hourlyLists: ArrayList<Hourly>) {
        this.hourlyList = hourlyLists
        notifyDataSetChanged()
    }

}
