package com.akshar.moviereviews.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akshar.moviereviews.R;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private TextView movieTitle, movieOverview, movieReleaseDate, movieRating, movieLanguage, moviePopularity, movieVoteCount ,movieAgeLimit ,movieType,movieGenre;
    private ImageView moviePoster , backBtn;

    private View view;

    public DetailsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);

        moviePoster = view.findViewById(R.id.iv_movie_backdrop);
        movieTitle = view.findViewById(R.id.tv_movie_title);
        movieOverview = view.findViewById(R.id.tv_movie_overview);
        movieReleaseDate = view.findViewById(R.id.tv_release_year);
        movieLanguage = view.findViewById(R.id.tv_language);
        movieAgeLimit = view.findViewById(R.id.isAdult);
        movieType = view.findViewById(R.id.tv_type);
        movieGenre = view.findViewById(R.id.tv_genre);
        backBtn = view.findViewById(R.id.iv_back);

        // Getting the data from the bundle
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String overview = bundle.getString("overview");
        String releaseDate = bundle.getString("releaseDate");
        String language = bundle.getString("language");
        String ageLimit = bundle.getString("ageLimit");
        String type = bundle.getString("type");
        String genre = bundle.getString("genre");
        String posterPath = bundle.getString("posterPath");

        // Setting the data to the views
        movieTitle.setText(title);
        movieOverview.setText(overview);
        movieReleaseDate.setText(releaseDate);
        movieLanguage.setText(language);
        movieAgeLimit.setText(ageLimit);
        movieType.setText(type);
        movieGenre.setText(genre);

        // Setting the poster image
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + posterPath).into(moviePoster);

        backBtn.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        return view;
    }
}