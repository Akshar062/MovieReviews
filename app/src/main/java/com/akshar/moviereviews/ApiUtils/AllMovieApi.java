package com.akshar.moviereviews.ApiUtils;

import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.Models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AllMovieApi {

    @GET("movie/popular")
    Call<MovieModel> getAllMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("trending/all/week")
    Call<AllModel> getTrendingAll(@Query("api_key") String apiKey);

    @GET("trending/movie/week")
    Call<AllModel> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("trending/person/week")
    Call<AllModel> getTrendingPeople(@Query("api_key") String apiKey);

    @GET("trending/tv/week")
    Call<AllModel> getTrendingTv(@Query("api_key") String apiKey);
}
