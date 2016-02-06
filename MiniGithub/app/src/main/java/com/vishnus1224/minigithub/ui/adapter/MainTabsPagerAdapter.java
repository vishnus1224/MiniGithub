package com.vishnus1224.minigithub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the view pager on the home activity.
 * Created by Vishnu on 2/6/2016.
 */
public class MainTabsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfPages;

    private List<Fragment> pagerFragments = new ArrayList<>();

    private String[] pageTitles;

    public MainTabsPagerAdapter(FragmentManager fragmentManager, List<Fragment> pagerFragments, String[] pageTitles, int numberOfPages) {

        super(fragmentManager);

        if(numberOfPages > 0){

            //throw an exception if the fragment list is null or empty.
            if(pagerFragments == null || pagerFragments.isEmpty()) {
                throw new IllegalStateException(numberOfPages + " pages must have " + numberOfPages + " fragments to display the content");
            }

            int titlesLength = pageTitles.length;

            //throw an exception if the titles and pages vary in number.
            if(titlesLength == 0 || numberOfPages != titlesLength){
                throw new IllegalStateException("Number of titles should be equal to the number of pages. " +
                numberOfPages + " != " + titlesLength);
            }
        }


        this.pagerFragments = pagerFragments;

        this.pageTitles = pageTitles;

        this.numberOfPages = numberOfPages;

    }

    @Override
    public Fragment getItem(int position) {
        return pagerFragments.get(position);
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
