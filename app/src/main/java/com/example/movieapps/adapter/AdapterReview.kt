package com.example.movieapps.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.movieapps.MovieByGenreActivity
import com.example.movieapps.R
import com.example.movieapps.data.remote.DatabaseAPI
import com.example.movieapps.model.GenreModel
import com.example.movieapps.model.MovieModel
import com.example.movieapps.model.ReviewModel
import com.example.movieapps.util.makeTextViewResizable
import org.json.JSONArray
import org.json.JSONObject

@SuppressLint("NotifyDataSetChanged")
class AdapterReview(var context: Context) : RecyclerView.Adapter<AdapterReview.ReviewHolder>() {
    var list: ArrayList<ReviewModel.ResultsEntity> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPost(list: ArrayList<ReviewModel.ResultsEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        return ReviewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_review, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        var modelList:ReviewModel.ResultsEntity = list[position]
        holder.txtName.text = modelList.author.toString()
        holder.txtReview.text = modelList.content.toString()
        makeTextViewResizable(holder.txtReview,5,"See More", true)
        holder.txtDate.text = modelList.createdAt.toString()
        holder.ratingReview.rating = modelList.authorDetails!!.rating.toFloat()
        Glide.with(context).load(DatabaseAPI.getPosterUrl(modelList.authorDetails!!.avatarPath.toString())).placeholder(R.drawable.ic_baseline_account_circle_24).error(R.drawable.ic_baseline_account_circle_24)
            .circleCrop()
            .into(holder.imgAvatar)
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ReviewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtName: TextView = itemView!!.findViewById(R.id.author)
        var txtDate: TextView = itemView!!.findViewById(R.id.date)
        var txtReview: TextView = itemView!!.findViewById(R.id.reviewText)
        var ratingReview: RatingBar = itemView!!.findViewById(R.id.rating)
        var imgAvatar: ImageView = itemView!!.findViewById(R.id.avatar)

    }
}