package com.akshar.moviereviews.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.akshar.moviereviews.Models.AllMovieModel;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.Utils.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<AllMovieModel.Result> resultList;

    public MovieAdapter(Context context, List<AllMovieModel.Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        AllMovieModel.Result result = resultList.get(position);

        holder.progressBar.setVisibility(View.VISIBLE);
        
        // Set data to views
        holder.movieName.setText(result.getTitle());
        holder.movieRating.setText(String.valueOf(result.getVoteAverage()));
        holder.movieReleaseDate.setText(result.getReleaseDate());

        // Load movie image using Picasso (or Glide)
        Picasso.get()
                .load(Constants.imageUrl + result.getPosterPath())// Set your placeholder image
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

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void updateData(List<AllMovieModel.Result> newData) {
        // Clear the existing data and add new data
        resultList.clear();
        resultList.addAll(newData);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView movieImage;
        TextView movieName, movieRating, movieReleaseDate;

        ProgressBar progressBar;

        public MovieViewHolder(@NonNull View itemView) {
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
