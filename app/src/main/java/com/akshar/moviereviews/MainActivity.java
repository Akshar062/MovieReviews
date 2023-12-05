package com.akshar.moviereviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.akshar.moviereviews.Models.AllModel;
import com.akshar.moviereviews.adapters.MovieAdapter;
import com.akshar.moviereviews.adapters.ViewPagerAdapter;
import com.akshar.moviereviews.fragments.DetailsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //Define parameters here
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize parameters here
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        movieAdapter = new MovieAdapter(this, resultList);
        movieAdapter.setOnItemClickListener(this);

        @Override
        public void onItemClick(AllModel.Result result) {
            // Open DetailsFragment and pass the selected result
            openDetailsFragment(result);
        }

        //Set up the BottomNavigationView here

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                viewPager.setCurrentItem(0);
            } else if (item.getItemId() == R.id.search) {
                viewPager.setCurrentItem(1);
            } else if (item.getItemId() == R.id.profile) {
                viewPager.setCurrentItem(2);
            } else if (item.getItemId() == R.id.settings) {
                viewPager.setCurrentItem(3);
            }
            return true;
        });

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    bottomNavigationView.setSelectedItemId(R.id.home);
                } else if (position == 1) {
                    bottomNavigationView.setSelectedItemId(R.id.search);
                } else if (position == 2) {
                    bottomNavigationView.setSelectedItemId(R.id.profile);
                } else if (position == 3) {
                    bottomNavigationView.setSelectedItemId(R.id.settings);
                }
            }
        });
    }

    private void openDetailsFragment(AllModel.Result result) {
        // Create a new instance of DetailsFragment and pass the selected result
        DetailsFragment detailsFragment = DetailsFragment.newInstance(result);
        // Perform a fragment transaction to replace the current fragment with DetailsFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailsFragment);
        transaction.addToBackStack(null); // Optional: Add to back stack for fragment navigation
        transaction.commit();
    }
}
