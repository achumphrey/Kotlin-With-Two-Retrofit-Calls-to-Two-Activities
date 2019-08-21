package com.example.kotlinwithtworetrofitcallstotwoactivities


import com.example.kotlinwithtworetrofitcallstotwoactivities.model.gitHub.ModelRepo
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientInterface {

    @GET("/users/{user}/repos")
    fun getUserRepo(@Path("user") user : String): Call<List<ModelRepo>>

    @GET("movie/popular")
    fun getmoviesPopular(@Query("api_key") apiKey : String): Call<MoviePopular>
}
