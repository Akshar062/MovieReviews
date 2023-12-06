package com.akshar.moviereviews.Utils;

import java.util.HashMap;
import java.util.Map;

public class GenreHelper {

    private static final Map<Integer, String> movieGenreMap;
    private static final Map<Integer, String> tvGenreMap;

    static {
        movieGenreMap = new HashMap<>();
        movieGenreMap.put(28, "Action");
        movieGenreMap.put(12, "Adventure");
        movieGenreMap.put(16, "Animation");
        movieGenreMap.put(35, "Comedy");
        movieGenreMap.put(80, "Crime");
        movieGenreMap.put(99, "Documentary");
        movieGenreMap.put(18, "Drama");
        movieGenreMap.put(10751, "Family");
        movieGenreMap.put(14, "Fantasy");
        movieGenreMap.put(36, "History");
        movieGenreMap.put(27, "Horror");
        movieGenreMap.put(10402, "Music");
        movieGenreMap.put(9648, "Mystery");
        movieGenreMap.put(10749, "Romance");
        movieGenreMap.put(878, "Science Fiction");
        movieGenreMap.put(10770, "TV Movie");
        movieGenreMap.put(53, "Thriller");
        movieGenreMap.put(10752, "War");
        movieGenreMap.put(37, "Western");

        tvGenreMap = new HashMap<>();
        tvGenreMap.put(10759, "Action & Adventure");
        tvGenreMap.put(16, "Animation");
        tvGenreMap.put(35, "Comedy");
        tvGenreMap.put(80, "Crime");
        tvGenreMap.put(99, "Documentary");
        tvGenreMap.put(18, "Drama");
        tvGenreMap.put(10751, "Family");
        tvGenreMap.put(10762, "Kids");
        tvGenreMap.put(9648, "Mystery");
        tvGenreMap.put(10763, "News");
        tvGenreMap.put(10764, "Reality");
        tvGenreMap.put(10765, "Sci-Fi & Fantasy");
        tvGenreMap.put(10766, "Soap");
        tvGenreMap.put(10767, "Talk");
        tvGenreMap.put(10768, "War & Politics");
        tvGenreMap.put(37, "Western");
    }

    public static String getMovieGenreById(int genreId) {
        return movieGenreMap.get(genreId);
    }

    public static String getTvGenreById(int genreId) {
        return tvGenreMap.get(genreId);
    }

    // Additional method to get all movie genres
    public static Map<Integer, String> getAllMovieGenres() {
        return movieGenreMap;
    }

    // Additional method to get all TV genres
    public static Map<Integer, String> getAllTvGenres() {
        return tvGenreMap;
    }

    public static void main(String[] args) {
    }
}
