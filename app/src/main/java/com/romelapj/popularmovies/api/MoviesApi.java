package com.romelapj.popularmovies.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("popular")
    public Call<List<String>> getPopularMovies();

    @GET("top_rated")
    public Call<List<String>> getToRatedMovies();

}
