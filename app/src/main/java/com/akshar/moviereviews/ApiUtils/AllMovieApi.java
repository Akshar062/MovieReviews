package com.akshar.moviereviews.ApiUtils;

import com.akshar.moviereviews.Models.AllMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface AllMovieApi {

    @GET("movie/popular")
    Call<AllMovieModel> getAllMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("trending/all/week")
    Call<AllMovieModel> getTrendingAll(@Query("api_key") String apiKey);

    @GET("trending/movie/week")
    Call<AllMovieModel> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("trending/person/week")
    Call<AllMovieModel> getTrendingPeople(@Query("api_key") String apiKey);

    @GET("trending/tv/week")
    Call<AllMovieModel> getTrendingTv(@Query("api_key") String apiKey);
}
