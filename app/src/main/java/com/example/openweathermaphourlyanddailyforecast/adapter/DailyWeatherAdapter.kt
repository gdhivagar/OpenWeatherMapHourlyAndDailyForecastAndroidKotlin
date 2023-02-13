package com.example.openweathermaphourlyanddailyforecast.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermaphourlyanddailyforecast.R
import com.example.openweathermaphourlyanddailyforecast.utils.DateFormatWeather
import com.example.openweathermaphourlyanddailyforecast.weatherhourlyresponse.Daily
import kotlinx.android.synthetic.main.item_daily.view.*
import java.util.*

class DailyWeatherAdapter(var context: Context) :
    RecyclerView.Adapter<DailyWeatherAdapter.MyViewHolder>() {

    private var dailyList = ArrayList<Daily>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_daily, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(dailyList[position], context, position)

    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(daily: Daily, context: Context, position: Int) {

            val getConvertedDate = daily.dt.let {
                DateFormatWeather.getDateTime(
                    it.toString(),
                    "EE \nd/MM"
                )
            }

            view.tvDailyWeatherDate.text = getConvertedDate

            for (j in 0 until daily.weather?.size!!) {

                val getValue = daily.weather!![j].icon

                when (getValue) {
                    "01d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_01d)
                    }
                    "01n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_01n)
                    }
                    "02d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_02d)
                    }
                    "02n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_02n)
                    }
                    "03d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_03d)
                    }
                    "03n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_03n)
                    }
                    "04d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_04d)
                    }
                    "04n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_04n)
                    }
                    "09d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_09d)
                    }
                    "09n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_09n)
                    }
                    "10d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_10d)
                    }
                    "10n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_10n)
                    }
                    "11d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_11d)
                    }
                    "11n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_11n)
                    }
                    "13d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_13d)
                    }
                    "13n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_13n)
                    }
                    "50d" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_50d)
                    }
                    "50n" -> {
                        view.ivDailyWeatherIcon.setBackgroundResource(R.drawable.ic_50n)
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dailyLists: ArrayList<Daily>) {
        this.dailyList = dailyLists
        notifyDataSetChanged()
    }

}
