package com.romelapj.popularmovies.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesClient {

    private static String BASE_URL = "http://api.themoviedb.org/3/movie/";

    public static MoviesApi create() {
        return create(HttpUrl.parse(BASE_URL));
    }

    private static MoviesApi create(HttpUrl httpUrl) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi.class);
    }
}
