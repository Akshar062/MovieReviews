package com.akshar.moviereviews.adapters;

import android.content.Context;
import android.os.Bundle;
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
import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.Utils.Constants;
import com.akshar.moviereviews.fragments.DetailsFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<AllModel.Result> resultList;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter(Context context, List<AllModel.Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    public interface OnItemClickListener {
        void onItemClick(AllModel.Result result);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        AllModel.Result result = resultList.get(position);

        holder.cardView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(result);
            }
        });

        holder.progressBar.setVisibility(View.VISIBLE);
        switch (result.getMediaType()) {
            case "movie":
                holder.movieName.setText(result.getTitle());
                holder.movieReleaseDate.setText(result.getReleaseDate());
                holder.movieRating.setText(String.valueOf(result.getVoteAverage()));
                setImage(holder,result.getPosterPath());
                break;
            case "tv":
                holder.movieName.setText(result.getName());
                holder.movieReleaseDate.setText(result.getFirstAirDate());
                holder.movieRating.setText(String.valueOf(result.getVoteAverage()));
                setImage(holder,result.getPosterPath());
                break;
            case "person":
                holder.movieName.setText(result.getName());
                holder.movieReleaseDate.setText(result.getKnownForDepartment());
                holder.movieRating.setText(String.valueOf(result.getPopularity()));
                setImage(holder,result.getProfilePath());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void updateData(List<AllModel.Result> newData) {
        // Clear the existing data and add new data
        resultList.clear();
        resultList.addAll(newData);
        notifyDataSetChanged();
    }

    private void setImage(MovieViewHolder holder ,String path) {
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
