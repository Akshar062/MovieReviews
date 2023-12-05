package com.akshar.moviereviews.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.akshar.moviereviews.fragments.HomeFragment;
import com.akshar.moviereviews.fragments.ProfileFragment;
import com.akshar.moviereviews.fragments.SearchFragment;
import com.akshar.moviereviews.fragments.SettingFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new ProfileFragment();
            case 3:
                return new SettingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
