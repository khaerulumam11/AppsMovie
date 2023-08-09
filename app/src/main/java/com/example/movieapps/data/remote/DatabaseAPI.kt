package com.example.movieapps.data.remote

object DatabaseAPI {
    const val API_VERSION: Int = 3
    private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    private const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    private const val BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185"
    private const val BASE_YT_IMG_URL = "https://img.youtube.com/vi/"
    private const val BASE_YT_WATCH_URL = "https://www.youtube.com/watch?v="
    const val BASE_API_URL = "https://api.themoviedb.org/$API_VERSION"
    const val MAX_RATING = 10f
    const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NTFlNjUzZDJhM2MwZGMwYTA1ODM2YmFhYjkxMjZhZSIsInN1YiI6IjVkODYzZTc0ZTc4ZTJkMDAyMTc0ZTQ5OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yK4MaD5sVtWSzrGbUi2ELU73haVEnQ6ua58h3XcqqjM"

    fun getPosterUrl(path: String) = BASE_POSTER_URL + path
    fun getBackdropUrl(path: String) = BASE_BACKDROP_URL + path
    fun getProfileUrl(path: String) = BASE_PROFILE_URL + path
    fun getYoutubeImageUrl(youtubeId: String) = "$BASE_YT_IMG_URL$youtubeId/hqdefault.jpg"
    fun getYoutubeWatchUrl(youtubeId: String) = "$BASE_YT_WATCH_URL$youtubeId"/**/
    fun getRuntimeDateFormat() = ("yyyy-MM-dd")
}