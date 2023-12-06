package com.akshar.moviereviews.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleModel {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Person> results;
    public int getPage() {
        return page;
    }
    public List<Person> getResults() {
        return results;
    }
    public static class Person {

        @SerializedName("adult")
        private boolean adult;

        @SerializedName("gender")
        private int gender;

        @SerializedName("id")
        private long id;

        @SerializedName("known_for_department")
        private String knownForDepartment;

        @SerializedName("name")
        private String name;

        @SerializedName("original_name")
        private String originalName;

        @SerializedName("popularity")
        private double popularity;

        @SerializedName("profile_path")
        private String profilePath;

        @SerializedName("known_for")
        private List<KnownFor> knownFor;

        public boolean isAdult() {
            return adult;
        }

        public int getGender() {
            return gender;
        }

        public long getId() {
            return id;
        }

        public String getKnownForDepartment() {
            return knownForDepartment;
        }

        public String getName() {
            return name;
        }

        public String getOriginalName() {
            return originalName;
        }

        public double getPopularity() {
            return popularity;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public List<KnownFor> getKnownFor() {
            return knownFor;
        }

        public static class KnownFor {
            @SerializedName("adult")
            private boolean adult;

            @SerializedName("backdrop_path")
            private String backdropPath;

            @SerializedName("id")
            private long id;

            @SerializedName("media_type")
            private String mediaType;

            @SerializedName("genre_ids")
            private List<Integer> genreIds;

            @SerializedName("popularity")
            private double popularity;

            @SerializedName("first_air_date")
            private String firstAirDate;

            @SerializedName("vote_average")
            private double voteAverage;

            @SerializedName("vote_count")
            private int voteCount;

            @SerializedName("origin_country")
            private List<String> originCountry;

            @SerializedName("title")
            private String title;

            @SerializedName("original_language")
            private String originalLanguage;

            @SerializedName("overview")
            private String overview;

            @SerializedName("poster_path")
            private String posterPath;

            @SerializedName("release_date")
            private String releaseDate;

            @SerializedName("video")
            private boolean video;

            @SerializedName("name")
            private String name;

            @SerializedName("original_name")
            private String originalName;

            public String getName() {
                return name;
            }

            public String getOriginalName() {
                return originalName;
            }

            // Add other fields based on your requirements

            public boolean isAdult() {
                return adult;
            }

            public String getBackdropPath() {
                return backdropPath;
            }

            public long getId() {
                return id;
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

            public String getFirstAirDate() {
                return firstAirDate;
            }

            public double getVoteAverage() {
                return voteAverage;
            }

            public int getVoteCount() {
                return voteCount;
            }

            public List<String> getOriginCountry() {
                return originCountry;
            }

            public String getTitle() {
                return title;
            }

            public String getOriginalLanguage() {
                return originalLanguage;
            }

            public String getOverview() {
                return overview;
            }

            public String getPosterPath() {
                return posterPath;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public boolean isVideo() {
                return video;
            }
        }
    }
}
