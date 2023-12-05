package com.akshar.moviereviews.Models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AllModel {

    public int page;

    @SerializedName("results")
    public List<Result> results;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;

    public static class Result {

        public boolean adult;

        @SerializedName("backdrop_path")
        public String backdropPath;

        public int id;

        // Common fields
        public String title;

        @SerializedName("original_language")
        public String originalLanguage;

        @SerializedName("original_title")
        public String originalTitle;

        public String overview;

        @SerializedName("poster_path")
        public String posterPath;

        @SerializedName("media_type")
        public String mediaType;

        @SerializedName("genre_ids")
        public List<Integer> genreIds;

        public double popularity;

        @SerializedName("release_date")
        public String releaseDate;

        @SerializedName("video")
        public boolean video;

        @SerializedName("vote_average")
        public double voteAverage;

        @SerializedName("vote_count")
        public int voteCount;

        // Movie-specific fields
        public String name;  // For TV shows

        @SerializedName("first_air_date")
        public String firstAirDate;  // For TV shows

        @SerializedName("origin_country")
        public List<String> originCountry;  // For TV shows

        // Person-specific fields
        @SerializedName("known_for_department")
        public String knownForDepartment;  // For people

        @SerializedName("profile_path")
        public String profilePath;  // For people

        @SerializedName("gender")
        public int gender;  // For people

        @SerializedName("known_for")
        public List<KnownFor> knownFor;  // For people

        public boolean isAdult() {
            return adult;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public String getOverview() {
            return overview;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getMediaType() {
            return mediaType;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public double getPopularity() {
            return popularity;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public boolean isVideo() {
            return video;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public String getName() {
            return name;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public List<String> getOriginCountry() {
            return originCountry;
        }

        public String getKnownForDepartment() {
            return knownForDepartment;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public int getGender() {
            return gender;
        }

        public List<KnownFor> getKnownFor() {
            return knownFor;
        }

        public static class KnownFor {
            public boolean adult;

            @SerializedName("backdrop_path")
            public String backdropPath;

            public int id;

            public String title;

            @SerializedName("original_language")
            public String originalLanguage;

            @SerializedName("original_title")
            public String originalTitle;

            public String overview;

            @SerializedName("poster_path")
            public String posterPath;

            @SerializedName("media_type")
            public String mediaType;

            @SerializedName("genre_ids")
            public List<Integer> genreIds;

            public double popularity;

            @SerializedName("release_date")
            public String releaseDate;

            @SerializedName("video")
            public boolean video;

            @SerializedName("vote_average")
            public double voteAverage;

            @SerializedName("vote_count")
            public int voteCount;

            public boolean isAdult() {
                return adult;
            }

            public String getBackdropPath() {
                return backdropPath;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getOriginalLanguage() {
                return originalLanguage;
            }

            public String getOriginalTitle() {
                return originalTitle;
            }

            public String getOverview() {
                return overview;
            }

            public String getPosterPath() {
                return posterPath;
            }

            public String getMediaType() {
                return mediaType;
            }

            public List<Integer> getGenreIds() {
                return genreIds;
            }

            public double getPopularity() {
                return popularity;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public boolean isVideo() {
                return video;
            }

            public double getVoteAverage() {
                return voteAverage;
            }

            public int getVoteCount() {
                return voteCount;
            }
        }

    }

    public int getPage() {
        return page;
    }

    public List<Result> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}

