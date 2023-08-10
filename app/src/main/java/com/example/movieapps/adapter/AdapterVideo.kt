package com.example.movieapps.adapter

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.remote.DatabaseAPI.getYoutubeImageUrl
import com.example.movieapps.data.remote.DatabaseAPI.getYoutubeWatchUrl
import com.example.movieapps.model.VideoModel


@SuppressLint("NotifyDataSetChanged")
class AdapterVideo(var context: Context) : RecyclerView.Adapter<AdapterVideo.VideoHolder>() {
    var list: ArrayList<VideoModel.ResultsEntity> = ArrayList()
    init {
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPost(list: ArrayList<VideoModel.ResultsEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        return VideoHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        var modelList:VideoModel.ResultsEntity = list[position]
        Glide.with(context).load(getYoutubeImageUrl(modelList.key.toString())).into(holder.imageVideo)
        holder.imageButton.setOnClickListener {
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getYoutubeWatchUrl(modelList.key.toString()))
            )
            try {
                context.startActivity(webIntent)
            } catch (ex: ActivityNotFoundException) {
                println("Activity Not Found")
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VideoHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var imageVideo: ImageView = itemView!!.findViewById(R.id.imageVideo)
        var imageButton: ImageButton = itemView!!.findViewById(R.id.playImageButton)

    }
}