package com.example.movieapps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.adapter.AdapterMovie
import com.example.movieapps.databinding.ActivityMovieByGenreBinding
import com.example.movieapps.model.MovieModel
import com.example.movieapps.viewModel.MainViewModel

class MovieByGenreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieByGenreBinding
    private lateinit var viewModel : MainViewModel
    private var adapterMovie : AdapterMovie?=null
    private var movieLiveData = ArrayList<MovieModel.ResultsEntity>()
    var page = 1
    var loading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieByGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = intent.getStringExtra("name")
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24)
        binding.toolbar.setNavigationOnClickListener { finish() }
        adapterMovie = AdapterMovie(this@MovieByGenreActivity)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getDiscoverMovie(page)
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieLiveData.clear()
            for(a in movieList.indices){
                if (movieList[a].genreIds!!.contains(intent.getIntExtra("id",0))){
                    movieLiveData.add(movieList[a])
                }
            }
            adapterMovie!!.setPost(movieLiveData )
            binding.loading.hide()
        })

        binding.rvList.layoutManager = LinearLayoutManager(this@MovieByGenreActivity)
        binding.rvList.adapter = adapterMovie

        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!loading) {
                    if (linearLayoutManager!!.findLastCompletelyVisibleItemPosition() == movieLiveData.size - 1) {
                        //bottom of list!

                        movieLiveData.clear()
                        binding.loading.show()
                        page+=1
                        viewModel.getDiscoverMovie(page)
                        viewModel.observeMovieLiveData().observe(this@MovieByGenreActivity, Observer { movieList ->
                           var listMovie = ArrayList<MovieModel.ResultsEntity>()
                            for(a in movieList.indices){
                                if (movieList[a].genreIds!!.contains(intent.getIntExtra("id",0))){
                                    listMovie.add(movieList[a])
                                }
                            }
                            adapterMovie!!.setPost(listMovie)
                            binding.loading.hide()
                        })
                        binding.rvList.post { // There is no need to use notifyDataSetChanged()
                            adapterMovie!!.notifyItemChanged(movieLiveData.size-1)
                        }
                        loading = true
                    }
                } else {
                    if (linearLayoutManager!!.findFirstCompletelyVisibleItemPosition() == 0) {

                        movieLiveData.clear()
                        binding.loading.show()
                        page-=1
                        viewModel.getDiscoverMovie(page)
                        viewModel.observeMovieLiveData().observe(this@MovieByGenreActivity, Observer { movieList ->
                            var listMovie = ArrayList<MovieModel.ResultsEntity>()
                            for(a in movieList.indices){
                                if (movieList[a].genreIds!!.contains(intent.getIntExtra("id",0))){
                                    listMovie.add(movieList[a])
                                }
                            }
                            adapterMovie!!.setPost(listMovie)
                            binding.loading.hide()
                        })
                        binding.rvList.post { // There is no need to use notifyDataSetChanged()
                            adapterMovie!!.notifyItemChanged(movieLiveData.size-1)
                        }
                        loading = false
                    }
                }
            }
        })
    }
}