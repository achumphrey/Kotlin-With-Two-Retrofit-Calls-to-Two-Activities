package com.example.kotlinwithtworetrofitcallstotwoactivities.network.movie


import com.example.kotlinwithtworetrofitcallstotwoactivities.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetrofitInstance {

    val retrofitInstance: Retrofit

        get() {

            val loggingInterceptor = HttpLoggingInterceptor()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor((loggingInterceptor))
                .build()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return   Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_MOVIE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

}