package com.akshar.moviereviews.ApiUtils;

import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.Models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AllMovieApi {

    @GET("trending/all/week")
    Call<AllModel> getTrendingAll(@Query("api_key") String apiKey);

    @GET("trending/movie/week")
    Call<AllModel> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("trending/person/week")
    Call<AllModel> getTrendingPeople(@Query("api_key") String apiKey);

    @GET("trending/tv/week")
    Call<AllModel> getTrendingTv(@Query("api_key") String apiKey);

    @GET("search/multi")
    Call<AllModel> multiSearch(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") int page
    );

    // Get recommended movies for a specific movie
    @GET("movie/{movie_id}/recommendations")
    Call<AllModel> getRecommendedMovies(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("page") int page
    );


    @GET("movie/upcoming")
    Call<AllModel> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/now_playing")
    Call<AllModel> getNowPlayingMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/top_rated")
    Call<AllModel> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/popular")
    Call<AllModel> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

}
