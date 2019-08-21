package com.example.kotlinwithtworetrofitcallstotwoactivities.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinwithtworetrofitcallstotwoactivities.R
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.gitHub.ModelRepo
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.MoviePopular
import com.example.kotlinwithtworetrofitcallstotwoactivities.model.movie.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.github_repo_recyclerviewer.view.*
import kotlinx.android.synthetic.main.recyclerviewer_layout.view.*

class RepoAdapter (private val gitRepoModel: List<ModelRepo>, private val listener: OnRepoClickLister)
        : RecyclerView.Adapter<RepoViewHolder>(){


       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

           return RepoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.github_repo_recyclerviewer, parent, false))
        }

        override fun getItemCount(): Int {
            return gitRepoModel.size
        }

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

            holder.tvName.text = gitRepoModel[position].name
            holder.tvFullName.text = gitRepoModel[position].full_name
            holder.tvURL.text = gitRepoModel[position].url


       //     holder.bind(gitRepoModel, listener)
        }
    }

    class RepoViewHolder (view: View): RecyclerView.ViewHolder(view){

        fun bind (results: Results, listener: OnRepoClickLister){
            itemView.setOnClickListener{
                listener.onRepoClick(results)
            }
        }

        val tvName = view.tv_name
        val imgView = view.iv_image_repo
        val tvFullName = view.tv_fullname
        val tvURL = view.tv_url
    }

    interface OnRepoClickLister{

        fun onRepoClick(results: Results)
    }
