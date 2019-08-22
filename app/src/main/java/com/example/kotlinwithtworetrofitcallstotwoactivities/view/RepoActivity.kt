package com.example.kotlinwithtworetrofitcallstotwoactivities.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinwithtworetrofitcallstotwoactivities.ClientInterface
import com.example.kotlinwithtworetrofitcallstotwoactivities.Constants
import com.example.kotlinwithtworetrofitcallstotwoactivities.R
import com.example.kotlinwithtworetrofitcallstotwoactivities.common.enqueue
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.gitHub.ModelRepo
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.MoviePopular
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.Results
import com.example.kotlinwithtworetrofitcallstotwoactivities.network.gitHub.GitRetrofitInstance
import com.example.kotlinwithtworetrofitcallstotwoactivities.network.movie.MovieRetrofitInstance
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.activity_repo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        val gitRepoRequest =
            GitRetrofitInstance().retrofitInstance.create(ClientInterface::class.java)

        val call = gitRepoRequest.getUserRepo(Constants.USER_NAME)

        // this is the extension function version of the callback enqueue

        call.enqueue {
            onResponse = {

                myGithubRepo -> val res = myGithubRepo.body()

                val adapter: RepoAdapter = RepoAdapter(res!!, object : OnRepoClickLister {
                    override fun onRepoClick(results: Results) {

                        Log.d("CLICKEDITEM", results.title)
                    }
                })
                rv_repo_list.layoutManager = LinearLayoutManager(this@RepoActivity)
                rv_repo_list.adapter = adapter
            }

            onFailure = {
                    error -> Log.d("Fail", error?.message)
            }
        }

        //this is the method without the extension function
        /*
        call.enqueue(object : Callback<List<ModelRepo>> {

            override fun onFailure(call: Call<List<ModelRepo>>, t: Throwable) {
                Log.d("FAIL", t.message)
            }

            override fun onResponse(call: Call<List<ModelRepo>>, response: Response<List<ModelRepo>>) {

                val res = response.body()
                Log.d("RepoActivity", res!![0].name)

                val adapter: RepoAdapter = RepoAdapter(res, object : OnRepoClickLister {
                    override fun onRepoClick(results: Results) {

                        Log.d("CLICKEDITEM", results.title)
                    }
                })
                rv_repo_list.layoutManager = LinearLayoutManager(this@RepoActivity)
                rv_repo_list.adapter = adapter

            }//END ONRESPONSE
         })//END CALL ENQUEUE
        */
    }
}
