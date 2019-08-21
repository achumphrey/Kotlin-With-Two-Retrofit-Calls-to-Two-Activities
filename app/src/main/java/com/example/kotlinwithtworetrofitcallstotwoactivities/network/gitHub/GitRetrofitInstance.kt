package com.example.kotlinwithtworetrofitcallstotwoactivities.network.gitHub


import com.example.kotlinwithtworetrofitcallstotwoactivities.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitRetrofitInstance {

    val retrofitInstance: Retrofit

        get() {

            val loggingInterceptor = HttpLoggingInterceptor()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor((loggingInterceptor))
                .build()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return   Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_REPO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

}