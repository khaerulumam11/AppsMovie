package com.example.movieapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.data.remote.DatabaseAPI.getPosterUrl
import com.example.movieapps.databinding.ItemListMovieBinding
import com.example.movieapps.model.MovieModel

class DiscoverMovieAdapter : RecyclerView.Adapter<DiscoverMovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<MovieModel.ResultsEntity>()
    fun setMovieList(movieList: List<MovieModel.ResultsEntity>) {
        this.movieList = movieList as ArrayList<MovieModel.ResultsEntity>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListMovieBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getPosterUrl( movieList[position].posterPath!!))
            .into(holder.binding.imgMovie)
        holder.binding.txtName.text = movieList[position].title
        holder.binding.txtGenre.text = movieList[position].genreIds.toString()
        holder.binding.txtPopularity.text = "${movieList[position].popularity} People Interested"
        holder.binding.txtDate.text = movieList[position].releaseDate
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}