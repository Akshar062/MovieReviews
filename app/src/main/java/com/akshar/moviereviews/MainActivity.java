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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize parameters here
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

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
}
