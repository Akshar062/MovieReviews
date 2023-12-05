package com.akshar.moviereviews.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akshar.moviereviews.ApiUtils.AllMovieApi;
import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.ApiUtils.RetrofitInstance;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.Utils.Constants;
import com.akshar.moviereviews.adapters.MovieAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        // Initializing the views
        recyclerView = view.findViewById(R.id.rv_home);
        tabLayout = view.findViewById(R.id.tab_layout);
        progressBar = view.findViewById(R.id.processing_bar);
        textView = view.findViewById(R.id.errorText);

        progressBar.setVisibility(View.VISIBLE);

        // Setting the Tabs
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Movies"));
        tabLayout.addTab(tabLayout.newTab().setText("TV Shows"));
        tabLayout.addTab(tabLayout.newTab().setText("Celebrities"));

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //set adapter to recycler view
        recyclerView.setAdapter(movieAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getTrendingData(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getTrendingData("All");

        // Get data from APi
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    void getTrendingData(String category) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllModel> call;

        switch (category) {
            case "All":
                call = allMovieApi.getTrendingAll(Constants.api_key);
                break;
            case "Movies":
                call = allMovieApi.getTrendingMovies(Constants.api_key);
                break;
            case "TV Shows":
                call = allMovieApi.getTrendingTv(Constants.api_key);
                break;
            case "Celebrities":
                call = allMovieApi.getTrendingPeople(Constants.api_key);
                break;
            default:
                return; // or handle default case accordingly
        }

        call.enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    AllModel movieModel = response.body();
                    if (movieModel != null) {
                        List<AllModel.Result> results = movieModel.getResults();
                        if (results != null) {
                            if (movieAdapter == null) {
                                movieAdapter = new MovieAdapter(getContext(), results);
                                recyclerView.setAdapter(movieAdapter);
                            } else {
                                movieAdapter.updateData(results);
                            }
                        } else {
                            textView.setVisibility(View.VISIBLE);
                            textView.setText("No data found");
                        }
                    } else {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Empty response");
                    }
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AllModel> call, Throwable t) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(t.getMessage());
                Log.d("TAG", "onFailure: status");
            }
        });
    }
}