package com.example.movieapps.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.MovieByGenreActivity
import com.example.movieapps.R
import com.example.movieapps.data.remote.DatabaseAPI
import com.example.movieapps.model.GenreModel
import com.example.movieapps.model.MovieModel
import org.json.JSONArray
import org.json.JSONObject

@SuppressLint("NotifyDataSetChanged")
class AdapterGenre(var context: Context) : RecyclerView.Adapter<AdapterGenre.GenreHolder>() {
    var list: ArrayList<GenreModel.GenresEntity> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPost(list: ArrayList<GenreModel.GenresEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        return GenreHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_genre, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        var modelList:GenreModel.GenresEntity = list[position]
        holder.txtName.text = modelList.name
        holder.itemView.setOnClickListener {
            var pindah = Intent(context, MovieByGenreActivity::class.java)
            pindah.putExtra("id", list[position].id)
            pindah.putExtra("name",list[position].name)
            context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class GenreHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtName: TextView = itemView!!.findViewById(R.id.txtName)

    }
}