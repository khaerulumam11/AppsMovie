package com.example.movieapps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.movieapps.data.remote.DatabaseAPI.BASE_API_URL
import com.example.movieapps.data.remote.DatabaseAPI.BEARER_TOKEN
import com.example.movieapps.model.MovieDetailModel
import com.example.movieapps.model.ReviewModel
import com.example.movieapps.model.VideoModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class MovieDetailViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<MovieDetailModel>()
    private var videoLiveData = MutableLiveData<List<VideoModel.ResultsEntity>>()
    private var reviewLiveData = MutableLiveData<List<ReviewModel.ResultsEntity>>()

    fun getDetailMovie(id: Int) {
        AndroidNetworking.get("$BASE_API_URL/movie/$id?language=en-US")
            .setPriority(Priority.HIGH)
            .addHeaders("Authorization", "Bearer $BEARER_TOKEN")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    println("Response $response")
                    var gsonBuilder = GsonBuilder().serializeNulls()
                    var gson: Gson? =  gsonBuilder.create()
                    var moviewCourseResp: MovieDetailModel = gson!!.fromJson(
                        response.toString(),
                        MovieDetailModel::class.java
                    )

                    movieLiveData.value = moviewCourseResp
                }

                override fun onError(anError: ANError) {
                    println("Erorr " + anError.message)
                }
            })
    }

    fun getListVideo(id: Int) {
        AndroidNetworking.get("$BASE_API_URL/movie/$id/videos?language=en-U")
            .setPriority(Priority.HIGH)
            .addHeaders("Authorization", "Bearer $BEARER_TOKEN")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    println("Response $response")
                    var gsonBuilder = GsonBuilder().serializeNulls()
                    var gson: Gson? =  gsonBuilder.create()
                    var videoResp: VideoModel = gson!!.fromJson(
                        response.toString(),
                        VideoModel::class.java
                    )

                    videoLiveData.value = videoResp.results
                }

                override fun onError(anError: ANError) {
                    println("Erorr " + anError.message)
                }
            })
    }

    fun getListReview(id: Int, page:Int) {
        AndroidNetworking.get("$BASE_API_URL/movie/$id/reviews?language=en-U")
            .setPriority(Priority.HIGH)
            .addHeaders("Authorization", "Bearer $BEARER_TOKEN")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    println("Response $response")
                    var gsonBuilder = GsonBuilder().serializeNulls()
                    var gson: Gson? =  gsonBuilder.create()
                    var reviewResp: ReviewModel = gson!!.fromJson(
                        response.toString(),
                        ReviewModel::class.java
                    )

                    reviewLiveData.value = reviewResp.results
                }

                override fun onError(anError: ANError) {
                    println("Erorr " + anError.message)
                }
            })
    }


    fun observeMovieLiveData() : LiveData<MovieDetailModel> {
        return movieLiveData
    }

    fun observeVideoLiveData() : LiveData<List<VideoModel.ResultsEntity>> {
        return videoLiveData
    }

    fun observeReviewLiveData() : LiveData<List<ReviewModel.ResultsEntity>> {
        return reviewLiveData
    }

}