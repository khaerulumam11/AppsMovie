package com.example.movieapps.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.movieapps.model.GenreModel.GenresEntity

class GenreModel {
    @Expose
    @SerializedName("genres")
    var genres: List<GenresEntity>? = null

    class GenresEntity {
        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("id")
        var id = 0
    }
}