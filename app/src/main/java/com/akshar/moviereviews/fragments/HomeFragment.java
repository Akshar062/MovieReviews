package com.akshar.moviereviews.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akshar.moviereviews.ApiUtils.AllMovieApi;
import com.akshar.moviereviews.Models.AllMovieModel;
import com.akshar.moviereviews.ApiUtils.RetrofitInstance;
import com.akshar.moviereviews.R;
import com.akshar.moviereviews.adapters.MovieAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    TabLayout tabLayout;
    MovieAdapter movieAdapter;

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
                if ("All".equals(tab.getText())) {
                    getTrendingAll();
                } else if ("Movies".equals(tab.getText())) {
                    getTrendingMovies();
                } else if ("TV Shows".equals(tab.getText())) {
                    getTrendingTv();
                } else if ("Celebrities".equals(tab.getText())) {
                    getTrendingCelebrities();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getTrendingAll();

        // Get data from APi
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

//    private void getAllMovies() {
//
//        Call<AllMovieModel> callAll = allMovieApi.getAllMovies("2322b4f0282ea32cb0faadfbd81a12e1","en-US", 1);
//        callAll.enqueue(new Callback<AllMovieModel>() {
//            @Override
//            public void onResponse(Call<AllMovieModel> call, Response<AllMovieModel> response) {
//                Log.d("TAG", "onResponse: status");
//
//                if (response.isSuccessful()) {
//                    AllMovieModel allMovieModel = response.body();
//
//                    if (allMovieModel != null) {
//                        List<AllMovieModel.Result> results = allMovieModel.getResults();
//
//                        if (results != null) {
//                            Log.d("TAG", "Results size: " + results.size());
//
//                            movieAdapter = new MovieAdapter(getContext(), results);
//                            recyclerView.setAdapter(movieAdapter);
//                        } else {
//                            Log.e("TAG", "Results list is null");
//                        }
//                    } else {
//                        Log.e("TAG", "Response body is null");
//                    }
//                } else {
//                    Log.e("TAG", "Response not successful. Code: " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllMovieModel> call, Throwable t) {
//                Log.d("TAG", "onFailure: status");
//            }
//        });
//    }

    void getTrendingAll() {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllMovieModel> callAll = allMovieApi.getTrendingAll("2322b4f0282ea32cb0faadfbd81a12e1");
        callAll.enqueue(new Callback<AllMovieModel>() {
            @Override
            public void onResponse(Call<AllMovieModel> call, Response<AllMovieModel> response) {
                Log.d("TAG", "onResponse: status");

                if (response.isSuccessful()) {
                    AllMovieModel allMovieModel = response.body();
                    if (allMovieModel != null) {
                        List<AllMovieModel.Result> results = allMovieModel.getResults();
                        if (results != null) {
                            Log.d("TAG", "Results size: " + results.size());
                            if (movieAdapter == null) {
                                movieAdapter = new MovieAdapter(getContext(), results);
                                recyclerView.setAdapter(movieAdapter);
                            } else {
                                movieAdapter.updateData(results);
                            }
                        } else {
                            Log.e("TAG", "Results list is null");
                        }
                    } else {
                        Log.e("TAG", "Response body is null");
                    }
                } else {
                    Log.e("TAG", "Response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AllMovieModel> call, Throwable t) {
                Log.d("TAG", "onFailure: status");
            }
        });
    }

    void getTrendingMovies() {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllMovieModel> callAll = allMovieApi.getTrendingMovies("2322b4f0282ea32cb0faadfbd81a12e1");
        callAll.enqueue(new Callback<AllMovieModel>() {
            @Override
            public void onResponse(Call<AllMovieModel> call, Response<AllMovieModel> response) {
                Log.d("TAG", "onResponse: status");

                if (response.isSuccessful()) {
                    AllMovieModel allMovieModel = response.body();
                    if (allMovieModel != null) {
                        List<AllMovieModel.Result> results = allMovieModel.getResults();
                        if (results != null) {
                            Log.d("TAG", "Results size: " + results.size());
                            if (movieAdapter == null) {
                                movieAdapter = new MovieAdapter(getContext(), results);
                                recyclerView.setAdapter(movieAdapter);
                            } else {
                                movieAdapter.updateData(results);
                            }
                        } else {
                            Log.e("TAG", "Results list is null");
                        }
                    } else {
                        Log.e("TAG", "Response body is null");
                    }
                } else {
                    Log.e("TAG", "Response not successful. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<AllMovieModel> call, Throwable t) {
                Log.d("TAG", "onFailure: status");
            }
        });
    }

    void getTrendingTv(){
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllMovieModel> callAll = allMovieApi.getTrendingTv("2322b4f0282ea32cb0faadfbd81a12e1");
        callAll.enqueue(new Callback<AllMovieModel>() {
            @Override
            public void onResponse(Call<AllMovieModel> call, Response<AllMovieModel> response) {
                Log.d("TAG", "onResponse: status");

                if (response.isSuccessful()) {
                    AllMovieModel allMovieModel = response.body();
                    if (allMovieModel != null) {
                        List<AllMovieModel.Result> results = allMovieModel.getResults();
                        if (results != null) {
                            Log.d("TAG", "Results size: " + results.size());
                            if (movieAdapter == null) {
                                movieAdapter = new MovieAdapter(getContext(), results);
                                recyclerView.setAdapter(movieAdapter);
                            } else {
                                movieAdapter.updateData(results);
                            }
                        } else {
                            Log.e("TAG", "Results list is null");
                        }
                    } else {
                        Log.e("TAG", "Response body is null");
                    }
                } else {
                    Log.e("TAG", "Response not successful. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<AllMovieModel> call, Throwable t) {
                Log.d("TAG", "onFailure: status");
            }
        });
    }

    void getTrendingCelebrities(){
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        AllMovieApi allMovieApi = retrofit.create(AllMovieApi.class);
        Call<AllMovieModel> callAll = allMovieApi.getTrendingPeople("2322b4f0282ea32cb0faadfbd81a12e1");
        callAll.enqueue(new Callback<AllMovieModel>() {
            @Override
            public void onResponse(Call<AllMovieModel> call, Response<AllMovieModel> response) {
                Log.d("TAG", "onResponse: status");

                if (response.isSuccessful()) {
                    AllMovieModel allMovieModel = response.body();
                    if (allMovieModel != null) {
                        List<AllMovieModel.Result> results = allMovieModel.getResults();
                        if (results != null) {
                            Log.d("TAG", "Results size: " + results.size());
                            if (movieAdapter == null) {
                                movieAdapter = new MovieAdapter(getContext(), results);
                                recyclerView.setAdapter(movieAdapter);
                            } else {
                                movieAdapter.updateData(results);
                            }
                        } else {
                            Log.e("TAG", "Results list is null");
                        }
                    } else {
                        Log.e("TAG", "Response body is null");
                    }
                } else {
                    Log.e("TAG", "Response not successful. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<AllMovieModel> call, Throwable t) {
                Log.d("TAG", "onFailure: status");
            }
        });
    }
}