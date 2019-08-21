package com.example.kotlinwithtworetrofitcallstotwoactivities.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlinwithtworetrofitcallstotwoactivities.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {

        when (view!!.id) {

            R.id.btn_go_github_repo -> {
                intent = Intent(this, RepoActivity::class.java)
            }

            R.id.btn_go_movies -> {
                intent = Intent(this, MoviesActivity::class.java)
            }
        }//END WHEN
        startActivity(intent)

    }//END ONCLICK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_go_github_repo.setOnClickListener(this)
        btn_go_movies.setOnClickListener(this)
    }
}
