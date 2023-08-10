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
import com.example.movieapps.model.GenreModel
import com.example.movieapps.model.MovieModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class MovieByGenreViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<MovieModel.ResultsEntity>>()

    fun getDiscoverMovie(page: Int) {
        AndroidNetworking.get("$BASE_API_URL/discover/movie?language=en-US&page=$page&sort_by=popularity.desc")
            .setPriority(Priority.HIGH)
            .addHeaders("Authorization", "Bearer $BEARER_TOKEN")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    println("Response $response")
                    var gsonBuilder = GsonBuilder().serializeNulls()
                    var gson: Gson? =  gsonBuilder.create()
                    var moviewCourseResp: MovieModel = gson!!.fromJson(
                        response.toString(),
                        MovieModel::class.java
                    )
                    movieLiveData.value = moviewCourseResp.results
                    println("Data Response $movieLiveData")
                }

                override fun onError(anError: ANError) {
                    println("Erorr " + anError.message)
                }
            })
    }


    fun observeMovieLiveData() : LiveData<List<MovieModel.ResultsEntity>> {
        return movieLiveData
    }

}