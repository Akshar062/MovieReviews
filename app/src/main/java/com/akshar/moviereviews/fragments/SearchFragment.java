package com.akshar.moviereviews.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akshar.moviereviews.ApiUtils.AllMovieApi;
import com.akshar.moviereviews.ApiUtils.RetrofitInstance;
import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.Utils.Constants;
import com.akshar.moviereviews.adapters.MovieAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchFragment extends Fragment {

    private View view;
    private RecyclerView nowPlayingRecyclerView, airingTodayRecyclerView ,searchRecyclerView;
    private MovieAdapter nowPlayingAdapter, airingTodayAdapter ,searchAdapter;

    private TextView searchTextView;
    private TextInputEditText searchEditText;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        nowPlayingRecyclerView = view.findViewById(R.id.rv_nowplaying);
        airingTodayRecyclerView = view.findViewById(R.id.rv_airingtoday);
        searchRecyclerView = view.findViewById(R.id.rv_search);
        searchEditText = view.findViewById(R.id.et_search);
        searchTextView = view.findViewById(R.id.tv_search);

        searchTextView.setVisibility(View.GONE);
        searchRecyclerView.setVisibility(View.GONE);



        RecyclerView.LayoutManager layoutManagerPlaying = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerAiring = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerSearch = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        nowPlayingRecyclerView.setLayoutManager(layoutManagerPlaying);
        airingTodayRecyclerView.setLayoutManager(layoutManagerAiring);
        searchRecyclerView.setLayoutManager(layoutManagerSearch);

        // Initialize the adapters before setting them to RecyclerViews
        nowPlayingRecyclerView.setAdapter(nowPlayingAdapter);
        airingTodayRecyclerView.setAdapter(nowPlayingAdapter);
        searchRecyclerView.setAdapter(searchAdapter);

        searchEditText.setOnFocusChangeListener((view, b) -> {
            String query = searchEditText.getText().toString();
            if (!b && !query.isEmpty()) {
                searchTextView.setVisibility(View.VISIBLE);
                searchRecyclerView.setVisibility(View.VISIBLE);
                searchData(query);
            } else {
                searchTextView.setVisibility(View.GONE);
                searchRecyclerView.setVisibility(View.GONE);
            }
        });
        getData();

        return view;
    }


    void searchData(String query){
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllModel> call;
        call = allMovieApi.multiSearch(Constants.api_key, query, 1);
        call.enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                if (response.isSuccessful()) {
                    AllModel movieModel = response.body();
                    if (movieModel != null) {
                        List<AllModel.Result> results = movieModel.getResults();
                        if (results != null) {
                            searchAdapter = new MovieAdapter(getContext(), results);
                            searchRecyclerView.setAdapter(searchAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllModel> call, Throwable t) {
                Log.d("TAG", "onFailure: status");
            }
        });
    }

    void getData() {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllModel> call;
        call = allMovieApi.getTrendingAll(Constants.api_key);
        call.enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                if (response.isSuccessful()) {
                    AllModel movieModel = response.body();
                    if (movieModel != null) {
                        List<AllModel.Result> results = movieModel.getResults();
                        if (results != null) {
                            nowPlayingAdapter = new MovieAdapter(getContext(), results);
                            nowPlayingRecyclerView.setAdapter(nowPlayingAdapter);
                            airingTodayRecyclerView.setAdapter(nowPlayingAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllModel> call, Throwable t) {
                Log.d("TAG", "onFailure: status");
            }
        });
    }
}
