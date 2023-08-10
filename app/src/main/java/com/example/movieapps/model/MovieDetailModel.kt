package com.example.movieapps.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.movieapps.model.MovieDetailModel.SpokenLanguagesEntity
import com.example.movieapps.model.MovieDetailModel.ProductionCountriesEntity
import com.example.movieapps.model.MovieDetailModel.ProductionCompaniesEntity
import com.example.movieapps.model.MovieDetailModel.BelongsToCollectionEntity

class MovieDetailModel {
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
    @SerializedName("tagline")
    var tagline: String? = null

    @Expose
    @SerializedName("status")
    var status: String? = null

    @Expose
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguagesEntity>? = null

    @Expose
    @SerializedName("runtime")
    var runtime = 0

    @Expose
    @SerializedName("revenue")
    var revenue = 0

    @Expose
    @SerializedName("release_date")
    var releaseDate: String? = null

    @Expose
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountriesEntity>? = null

    @Expose
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesEntity>? = null

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
    @SerializedName("imdb_id")
    var imdbId: String? = null

    @Expose
    @SerializedName("id")
    var id = 0

    @Expose
    @SerializedName("homepage")
    var homepage: String? = null

    @Expose
    @SerializedName("genres")
    var genres: List<GenresEntity>? = null

    @Expose
    @SerializedName("budget")
    var budget = 0

    @Expose
    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollectionEntity? = null

    @Expose
    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @Expose
    @SerializedName("adult")
    var adult = false

    class SpokenLanguagesEntity {
        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("iso_639_1")
        var iso6391: String? = null

        @Expose
        @SerializedName("english_name")
        var englishName: String? = null
    }

    class ProductionCountriesEntity {
        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("iso_3166_1")
        var iso31661: String? = null
    }

    class ProductionCompaniesEntity {
        @Expose
        @SerializedName("origin_country")
        var originCountry: String? = null

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("logo_path")
        var logoPath: String? = null

        @Expose
        @SerializedName("id")
        var id = 0
    }

    class GenresEntity {
        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("id")
        var id = 0
    }

    class BelongsToCollectionEntity {
        @Expose
        @SerializedName("backdrop_path")
        var backdropPath: String? = null

        @Expose
        @SerializedName("poster_path")
        var posterPath: String? = null

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("id")
        var id = 0
    }
}