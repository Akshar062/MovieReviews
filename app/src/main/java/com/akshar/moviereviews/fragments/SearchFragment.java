package com.akshar.moviereviews.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akshar.moviereviews.ApiUtils.AllMovieApi;
import com.akshar.moviereviews.ApiUtils.RetrofitInstance;
import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.Utils.Constants;
import com.akshar.moviereviews.adapters.MovieAdapter;
import com.akshar.moviereviews.adapters.SearchAdapter;
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
    private SearchAdapter nowPlayingAdapter, airingTodayAdapter ,searchAdapter;

    private TextView searchTextView , pageNumberTextView;
    private TextInputEditText searchEditText;

    private ImageView next, previous;
    private ConstraintLayout searchLayout;

    private int pageNumber = 1;

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
        searchLayout = view.findViewById(R.id.searchTitlecl);

        next = view.findViewById(R.id.next_page_search);
        previous = view.findViewById(R.id.previuse_page_search);
        pageNumberTextView = view.findViewById(R.id.tv_page_no_search);

        searchLayout.setVisibility(View.GONE);
        searchRecyclerView.setVisibility(View.GONE);

        if (pageNumber == 1) {
            previous.setVisibility(View.GONE);
        } else {
            previous.setVisibility(View.VISIBLE);
        }

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
                searchRecyclerView.setVisibility(View.VISIBLE);
                searchLayout.setVisibility(View.VISIBLE);
                searchData(query);
            } else {
                searchRecyclerView.setVisibility(View.GONE);
                searchLayout.setVisibility(View.GONE);
            }
        });


        next.setOnClickListener(view -> {
            pageNumber++;
            if (pageNumber == 1) {
                previous.setVisibility(View.GONE);
            } else {
                previous.setVisibility(View.VISIBLE);
            }
            pageNumberTextView.setText(String.valueOf(pageNumber));
            searchPage(pageNumber);
        });

        previous.setOnClickListener(view -> {
            if (pageNumber > 1) {
                pageNumber--;
                if (pageNumber == 1) {
                    previous.setVisibility(View.GONE);
                } else {
                    previous.setVisibility(View.VISIBLE);
                }
                pageNumberTextView.setText(String.valueOf(pageNumber));
                searchPage(pageNumber);
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
                            searchAdapter = new SearchAdapter(getContext(), results);
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

    void searchPage(int pageNumber){
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllModel> call;
        call = allMovieApi.multiSearch(Constants.api_key, searchEditText.getText().toString(), pageNumber);
        call.enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                if (response.isSuccessful()) {
                    AllModel movieModel = response.body();
                    if (movieModel != null) {
                        List<AllModel.Result> results = movieModel.getResults();
                        if (results != null) {
                            searchAdapter = new SearchAdapter(getContext(), results);
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
                            nowPlayingAdapter = new SearchAdapter(getContext(), results);
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
