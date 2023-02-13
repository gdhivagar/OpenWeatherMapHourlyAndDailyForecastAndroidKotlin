package com.example.openweathermaphourlyanddailyforecast.network

import com.example.openweathermaphourlyanddailyforecast.BuildConfig
import com.example.openweathermaphourlyanddailyforecast.utils.Vars.KEY_BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    val getClient: WebServices
        get() {

            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(
                60,
                TimeUnit.SECONDS
            )

            if (BuildConfig.DEBUG) {
                client.addInterceptor(interceptor)
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(KEY_BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(WebServices::class.java)
        }

}