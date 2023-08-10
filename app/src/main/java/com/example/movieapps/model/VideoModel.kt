package com.example.movieapps.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoModel {
    @Expose
    @SerializedName("results")
    var results: List<ResultsEntity>? = null

    @Expose
    @SerializedName("id")
    var id = 0

    class ResultsEntity {
        @Expose
        @SerializedName("id")
        var id: String? = null

        @Expose
        @SerializedName("published_at")
        var publishedAt: String? = null

        @Expose
        @SerializedName("official")
        var official = false

        @Expose
        @SerializedName("type")
        var type: String? = null

        @Expose
        @SerializedName("size")
        var size = 0

        @Expose
        @SerializedName("site")
        var site: String? = null

        @Expose
        @SerializedName("key")
        var key: String? = null

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("iso_3166_1")
        var iso31661: String? = null

        @Expose
        @SerializedName("iso_639_1")
        var iso6391: String? = null
    }
}