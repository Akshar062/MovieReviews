package com.akshar.moviereviews.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsFragment extends BottomSheetDialogFragment {

    private TextView movieTitle, movieOverview, movieReleaseDate, movieRating, movieLanguage, moviePopularity, movieVoteCount ,movieAgeLimit ,movieType,movieGenre,movieKnownForTitle , knownForType;
    private LinearLayout movieKnownForLayout;
    private ImageView moviePoster , movieKnownForPoster,closeBtn;
    private View view;

    public static final String TAG = "DetailsFragment";


    public DetailsFragment() {
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
        closeBtn = view.findViewById(R.id.close_btn);

        movieKnownForLayout = view.findViewById(R.id.ll_movie_known_for);
        movieKnownForTitle = view.findViewById(R.id.tv_movie_known_for);
        movieKnownForPoster = view.findViewById(R.id.iv_movie_poster);
        knownForType = view.findViewById(R.id.tv_movie_known_for_more);

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

        if (type.equals("person")){
            movieKnownForLayout.setVisibility(View.VISIBLE);
            String knownForTitle = bundle.getString("knownForTitle");
            String knownForPosterPath = bundle.getString("knownForPosterPath");
            movieKnownForTitle.setText(knownForTitle);
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + knownForPosterPath).into(movieKnownForPoster);
            String knownForTypeText = bundle.getString("knownForType");
            knownForType.setText(knownForTypeText);
        }

        // Setting the data to the views
        movieTitle.setText(title);
        movieOverview.setText(overview);
        movieReleaseDate.setText(releaseDate);
        movieLanguage.setText(language);

        if (ageLimit.equals("false")) {
            ageLimit = "U/A:13+";
            movieAgeLimit.setText(ageLimit);
        } else {
            ageLimit = "U/A:18+";
            movieAgeLimit.setText(ageLimit);
        }
        movieType.setText(type);
        movieGenre.setText(genre);
        // Setting the poster image
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + posterPath).into(moviePoster);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }
}