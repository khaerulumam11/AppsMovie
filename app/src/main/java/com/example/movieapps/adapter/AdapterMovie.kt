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
import com.example.movieapps.MovieDetailActivity
import com.example.movieapps.R
import com.example.movieapps.data.remote.DatabaseAPI
import com.example.movieapps.model.MovieModel
import org.json.JSONArray
import org.json.JSONObject

@SuppressLint("NotifyDataSetChanged")
class AdapterMovie(var context: Context) : RecyclerView.Adapter<AdapterMovie.MoviewHolder>() {
    var list: ArrayList<MovieModel.ResultsEntity> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPost(list: ArrayList<MovieModel.ResultsEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewHolder {
        return MoviewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviewHolder, position: Int) {
        var modelList:MovieModel.ResultsEntity = list[position]
        holder.txtName.text = modelList.title
        val jsonRawData = context.resources.openRawResource(
            context.resources.getIdentifier(
                "genre",
                "raw",
                context.packageName
            )
        ).bufferedReader().use { it.readText() }

        val output = JSONObject(jsonRawData)
        var outputArray = output.getJSONArray("genres")
        var genreString =""
        for (i in 0 until outputArray.length()){
            var id = outputArray.getJSONObject(i).getString("id").toString().toInt()
            var name = outputArray.getJSONObject(i).getString("name").toString()
            for(x in 0 until modelList.genreIds!!.size){
                if (modelList.genreIds!![x] == id){
                    genreString+=name
                    if (x < modelList.genreIds!!.size-1){
                        genreString+=", "
                    }
                }
            }
        }
        holder.txtGenre!!.text = genreString
        holder.txtPopular!!.text = "${modelList.popularity} People Interested"
        holder.txtDate!!.text = modelList.releaseDate
        Glide.with(holder.itemView)
            .load(DatabaseAPI.getPosterUrl(modelList.posterPath!!))
            .into(holder.imgMovie!!)
        holder.itemView.setOnClickListener {
            var pindah = Intent(context, MovieDetailActivity::class.java)
            pindah.putExtra("name", modelList.title)
            pindah.putExtra("id", modelList.id)
            context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MoviewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtName: TextView = itemView!!.findViewById(R.id.txtName)
        var txtPopular: TextView? = itemView!!.findViewById(R.id.txtPopularity)
        var txtDate: TextView? = itemView!!.findViewById(R.id.txtDate)
        var txtGenre: TextView? = itemView!!.findViewById(R.id.txtGenre)
        var imgMovie: ImageView? = itemView!!.findViewById(R.id.imgMovie)

    }
}