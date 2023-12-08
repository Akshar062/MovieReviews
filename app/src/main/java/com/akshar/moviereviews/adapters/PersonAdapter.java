package com.akshar.moviereviews.adapters;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.akshar.moviereviews.MainActivity;
import com.akshar.moviereviews.Models.PeopleModel;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.Utils.Constants;
import com.akshar.moviereviews.Utils.GenreHelper;
import com.akshar.moviereviews.fragments.DetailsFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context context;

    private List<PeopleModel.Person> resultList;
    public PersonAdapter(Context context, List<PeopleModel.Person> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new PersonViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PeopleModel.Person result = resultList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailsDialog(result);
            }
        });

        holder.progressBar.setVisibility(View.VISIBLE);
        holder.movieName.setText(result.getName());
        holder.movieReleaseDate.setText(result.getKnownForDepartment());
        holder.movieRating.setText(String.valueOf(result.getPopularity()));
        setImage(holder,result.getProfilePath());

    }

    private void showDetailsDialog(PeopleModel.Person result) {
        List<PeopleModel.Person.KnownFor> knownForList = result.getKnownFor();
        DetailsFragment detailsFragment = new DetailsFragment();
        // Pass data to the fragment using Bundle
        Bundle bundle = new Bundle();

        bundle.putString("title", result.getName());
        bundle.putString("releaseDate", result.getKnownForDepartment());
        bundle.putString("posterPath", result.getProfilePath());
        bundle.putString("overview", knownForList.get(0).getOverview());

        List<Integer> personGenreIds = knownForList.get(0).getGenreIds();
        String mediaType = knownForList.get(0).getMediaType();

        if ("movie".equals(mediaType)) {
            List<String> personGenres = getGenresAsString(personGenreIds, GenreHelper.getAllMovieGenres());
            bundle.putString("genre", personGenres != null ? TextUtils.join(", ", personGenres) : "");
            bundle.putString("knownForTitle", knownForList.get(0).getTitle());
        } else if ("tv".equals(mediaType)) {
            List<String> personGenres = getGenresAsString(personGenreIds, GenreHelper.getAllTvGenres());
            bundle.putString("genre", personGenres != null ? TextUtils.join(", ", personGenres) : "");
            bundle.putString("knownForTitle", knownForList.get(0).getName());
        }
        bundle.putString("knownForPosterPath", knownForList.get(0).getPosterPath());
        bundle.putString("knownForType", knownForList.get(0).getMediaType());
        bundle.putString("language", result.getOriginalName());
        bundle.putString("ageLimit", String.valueOf(result.isAdult()));
        bundle.putString("type", result.getKnownForDepartment());
        detailsFragment.setArguments(bundle);
        // Show the fragment
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        detailsFragment.show(transaction, DetailsFragment.TAG);
    }

    private List<String> getGenresAsString(List<Integer> genreIds, Map<Integer, String> genreMap) {
        if (genreIds != null && genreMap != null) {
            List<String> genres = new ArrayList<>();
            for (Integer genreId : genreIds) {
                String genreName = genreMap.get(genreId);
                if (genreName != null) {
                    genres.add(genreName);
                }
            }
            return genres;
        }
        return null;
    }
    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void updateData(List<PeopleModel.Person> newData) {
        // Clear the existing data and add new data
        resultList.clear();
        resultList.addAll(newData);
        notifyDataSetChanged();
    }

    private void setImage(PersonViewHolder holder ,String path) {
        Picasso.get()
                .load(Constants.imageUrl + path)// Set your placeholder image
                .error(R.drawable.ic_launcher_foreground)              // Set an error image if loading fails
                .into(holder.movieImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Hide progress bar on success
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        // Handle error if needed
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView movieImage;
        TextView movieName, movieRating, movieReleaseDate;

        ProgressBar progressBar;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            movieImage = itemView.findViewById(R.id.iv_movie_poster);
            movieName = itemView.findViewById(R.id.tv_movie_title);
            movieRating = itemView.findViewById(R.id.tv_movie_rating);
            movieReleaseDate = itemView.findViewById(R.id.tv_movie_release_date);

            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
