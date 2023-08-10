package com.example.movieapps.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewModel {
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

    @Expose
    @SerializedName("id")
    var id = 0

    class ResultsEntity {
        @Expose
        @SerializedName("url")
        var url: String? = null

        @Expose
        @SerializedName("updated_at")
        var updatedAt: String? = null

        @Expose
        @SerializedName("id")
        var id: String? = null

        @Expose
        @SerializedName("created_at")
        var createdAt: String? = null

        @Expose
        @SerializedName("content")
        var content: String? = null

        @Expose
        @SerializedName("author_details")
        var authorDetails: AuthorDetailsEntity? = null

        @Expose
        @SerializedName("author")
        var author: String? = null
    }

    class AuthorDetailsEntity {
        @Expose
        @SerializedName("rating")
        var rating = 0

        @Expose
        @SerializedName("avatar_path")
        var avatarPath: String? = null

        @Expose
        @SerializedName("username")
        var username: String? = null

        @Expose
        @SerializedName("name")
        var name: String? = null
    }
}