package com.example.kotlinwithtworetrofitcallstotwoactivities.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinwithtworetrofitcallstotwoactivities.ClientInterface
import com.example.kotlinwithtworetrofitcallstotwoactivities.Constants
import com.example.kotlinwithtworetrofitcallstotwoactivities.R
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.MoviePopular
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.Results
import com.example.kotlinwithtworetrofitcallstotwoactivities.network.movie.MovieRetrofitInstance
import kotlinx.android.synthetic.main.activity_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val movieRequest =
            MovieRetrofitInstance().retrofitInstance.create(ClientInterface::class.java)
        val call = movieRequest.getmoviesPopular(Constants.API_KEY)

        call.enqueue(object : Callback<MoviePopular> {

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
            }

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {

                val res = response.body()
                Log.d("MainActivity", res!!.results[0].title)

                val adapter: MovieAdapter = MovieAdapter(res, object : OnMovieClickLister {
                    override fun onMovieClick(results: Results) {

                        Log.d("CLICKEDITEM", results.title)
                    }
                })
                rv_list.layoutManager = LinearLayoutManager(this@MoviesActivity)
                rv_list.adapter = adapter
            }
        })
    }
}
