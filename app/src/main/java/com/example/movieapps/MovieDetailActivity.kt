package com.example.movieapps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.adapter.AdapterReview
import com.example.movieapps.adapter.AdapterVideo
import com.example.movieapps.data.remote.DatabaseAPI.getBackdropUrl
import com.example.movieapps.databinding.ActivityMovieDetailBinding
import com.example.movieapps.model.MovieModel
import com.example.movieapps.model.ReviewModel
import com.example.movieapps.model.VideoModel
import com.example.movieapps.viewModel.MainViewModel
import com.example.movieapps.viewModel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailBinding
    private lateinit var viewModel : MovieDetailViewModel
    private var adapterVideo : AdapterVideo ?=null
    private var adapterReview : AdapterReview?=null
    private var reviewLiveData = ArrayList<ReviewModel.ResultsEntity>()
    var page = 1
    var loading = false
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = intent.getStringExtra("name")
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24)
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.movieImageProgressBar.visibility = View.VISIBLE
        adapterVideo = AdapterVideo(this@MovieDetailActivity)
        adapterReview = AdapterReview(this@MovieDetailActivity)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.getDetailMovie(intent.getIntExtra("id",0))
        viewModel.observeMovieLiveData().observe(this, Observer { movieDetail ->
            binding.titleText.text = movieDetail.title
            var genreString = ""
            for(i in 0 until movieDetail.genres!!.size){
                genreString+=movieDetail.genres!![i].name
                if (i < movieDetail.genres!!.size-1){
                    genreString+=" / "
                }
            }
            binding.genresText.text = genreString
            binding.ratingBar.rating = movieDetail.voteAverage.toFloat()
            binding.numOfVotes.text = "${movieDetail.voteCount} votes"
            binding.episodeText.text = movieDetail.releaseDate.toString()
            binding.seasonText.text = "${movieDetail.runtime} mins"
            binding.airDateText.text = movieDetail.spokenLanguages!![0].name.toString()
            binding.overviewText.text = movieDetail.overview.toString()
            Glide.with(this@MovieDetailActivity).load(getBackdropUrl(movieDetail.backdropPath.toString())).into(binding.movieImage)
        })

        viewModel.getListVideo(intent.getIntExtra("id",0))
        viewModel.observeVideoLiveData().observe(this, Observer { videoList ->
            adapterVideo!!.setPost(videoList as ArrayList<VideoModel.ResultsEntity>)
        })

        binding.videosRecyclerView.layoutManager = LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.videosRecyclerView.adapter = adapterVideo

        viewModel.getListReview(intent.getIntExtra("id",0), page)
        viewModel.observeReviewLiveData().observe(this, Observer { reviewList ->
            reviewLiveData.clear()
            reviewLiveData.addAll(reviewList as ArrayList<ReviewModel.ResultsEntity>)
            adapterReview!!.setPost(reviewList as ArrayList<ReviewModel.ResultsEntity>)
            binding.movieImageProgressBar.visibility = View.GONE
        })

        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(this@MovieDetailActivity)
        binding.reviewRecyclerView.adapter = adapterReview

        binding.reviewRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!loading) {
                    if (linearLayoutManager!!.findLastCompletelyVisibleItemPosition() == reviewLiveData.size - 1) {
                        //bottom of list!

                        reviewLiveData.clear()
                        page+=1
                        viewModel.getListReview(intent.getIntExtra("id",0),page)
                        viewModel.observeReviewLiveData().observe(this@MovieDetailActivity, Observer { reviewList ->
                            adapterReview!!.setPost(reviewList as ArrayList<ReviewModel.ResultsEntity>)
                        })
                        binding.reviewRecyclerView.post { // There is no need to use notifyDataSetChanged()
                            adapterReview!!.notifyItemChanged(reviewLiveData.size-1)
                        }

                        loading = true
                    }
                } else {
                    if (linearLayoutManager!!.findFirstCompletelyVisibleItemPosition() == 0) {

                        reviewLiveData.clear()
                        page-=1
                        viewModel.getListReview(intent.getIntExtra("id",0),page)
                        viewModel.observeReviewLiveData().observe(this@MovieDetailActivity, Observer { reviewList ->
                            adapterReview!!.setPost(reviewList as ArrayList<ReviewModel.ResultsEntity>)

                        })
                        binding.reviewRecyclerView.post { // There is no need to use notifyDataSetChanged()
                            adapterReview!!.notifyItemChanged(reviewLiveData.size-1)
                        }

                        loading = false
                    }
                }
            }
        })
    }
}