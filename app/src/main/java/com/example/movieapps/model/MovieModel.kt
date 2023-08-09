package com.example.movieapps.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.movieapps.model.MovieModel.ResultsEntity

class MovieModel {
    @Expose
    @SerializedName("total_results")
    var totalResults = 0

    @Expose
    @SerializedName("total_pages")
    var totalPages = 0

    @Expose
    @SerializedName("results")
    var results: List<ResultsEntity>? = null

    @Expose
    @SerializedName("page")
    var page = 0

    class ResultsEntity {
        @Expose
        @SerializedName("vote_count")
        var voteCount = 0

        @Expose
        @SerializedName("vote_average")
        var voteAverage = 0.0

        @Expose
        @SerializedName("video")
        var video = false

        @Expose
        @SerializedName("title")
        var title: String? = null

        @Expose
        @SerializedName("release_date")
        var releaseDate: String? = null

        @Expose
        @SerializedName("poster_path")
        var posterPath: String? = null

        @Expose
        @SerializedName("popularity")
        var popularity = 0.0

        @Expose
        @SerializedName("overview")
        var overview: String? = null

        @Expose
        @SerializedName("original_title")
        var originalTitle: String? = null

        @Expose
        @SerializedName("original_language")
        var originalLanguage: String? = null

        @Expose
        @SerializedName("id")
        var id = 0

        @Expose
        @SerializedName("genre_ids")
        var genreIds: List<Int>? = null

        @Expose
        @SerializedName("backdrop_path")
        var backdropPath: String? = null

        @Expose
        @SerializedName("adult")
        var adult = false
    }
}