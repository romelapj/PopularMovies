package com.romelapj.popularmovies.api

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesApi {

    @GET("popular")
    fun getPopularMovies(): Call<List<String>>

    @GET("top_rated")
    fun getToRatedMovies(): Call<List<String>>

    companion object {
        private const val BASE_URL = "http://api.themoviedb.org/3/movie/"
        fun create(): MoviesApi = create(HttpUrl.parse(BASE_URL)!!)
        fun create(httpUrl: HttpUrl): MoviesApi {
            val client = OkHttpClient.Builder()
                    .build()
            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MoviesApi::class.java)
        }
    }
}