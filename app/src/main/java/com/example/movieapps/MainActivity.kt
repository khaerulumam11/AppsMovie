package com.example.movieapps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.adapter.AdapterMovie
import com.example.movieapps.databinding.ActivityMainBinding
import com.example.movieapps.model.MovieModel
import com.example.movieapps.viewModel.MainViewModel
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private var adapterMovie : AdapterMovie?=null
    private var movieLiveData = ArrayList<MovieModel.ResultsEntity>()
    var page = 1
    var loading = false
    var pastItemsVisible = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0
    private var gson: Gson? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = "Home"
        binding.loading.show()
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        adapterMovie = AdapterMovie(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getDiscoverMovie(page)
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieLiveData.clear()
            movieLiveData.addAll(movieList)
            adapterMovie!!.setPost(movieList as ArrayList<MovieModel.ResultsEntity>)
            binding.loading.hide()
        })
//        var layoutManager = LinearLayoutManager(this@MainActivity)
        binding.discoverRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.discoverRecyclerView.adapter = adapterMovie

        binding.discoverRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                var posisiAwal = linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                println("Posisi Awal $posisiAwal")
                if (!loading) {

                    var posisi = linearLayoutManager!!.findLastCompletelyVisibleItemPosition()
                    println("Posisi $posisi")
                    var posisiAwal = linearLayoutManager!!.findFirstVisibleItemPosition()
                    println("Posisi Awal $posisiAwal")
                    println("Posisi Data  "+movieLiveData.size)
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == movieLiveData.size - 1) {
                        //bottom of list!

                        movieLiveData.clear()
                        binding.loading.show()
                        page+=1
                        viewModel.getDiscoverMovie(page)
                        viewModel.observeMovieLiveData().observe(this@MainActivity, Observer { movieList ->
                            adapterMovie!!.setPost(movieList as ArrayList<MovieModel.ResultsEntity>)
                            binding.loading.hide()
                        })
                        binding.discoverRecyclerView.post { // There is no need to use notifyDataSetChanged()
                            adapterMovie!!.notifyItemChanged(movieLiveData.size-1)
                        }
                        loading = true
                    }
                } else {
                    if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {

                        movieLiveData.clear()
                        binding.loading.show()
                        page-=1
                        viewModel.getDiscoverMovie(page)
                        viewModel.observeMovieLiveData().observe(this@MainActivity, Observer { movieList ->
                            adapterMovie!!.setPost(movieList as ArrayList<MovieModel.ResultsEntity>)
                            binding.loading.hide()
                        })
                        binding.discoverRecyclerView.post { // There is no need to use notifyDataSetChanged()
                            adapterMovie!!.notifyItemChanged(movieLiveData.size-1)
                        }
                        loading = false
                    }
                }
            }
        })
    }

}