package com.vishnus1224.minigithub.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.config.TabConfig;
import com.vishnus1224.minigithub.generator.FragmentGenerator;
import com.vishnus1224.minigithub.ui.adapter.MainTabsPagerAdapter;
import com.vishnus1224.minigithub.ui.fragment.CodeFragment;
import com.vishnus1224.minigithub.ui.fragment.IssuesFragment;
import com.vishnus1224.minigithub.ui.fragment.RepositoriesFragment;
import com.vishnus1224.minigithub.ui.fragment.UsersFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private TabLayout homeTabLayout;

    private ViewPager homeTabViewPager;
    private MainTabsPagerAdapter mainTabsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        setupViewPager();

        setupTabs();

    }

    private void setupViews() {

        homeTabLayout = (TabLayout) findViewById(R.id.homeTabs);

        homeTabViewPager = (ViewPager) findViewById(R.id.homeTabPager);

    }

    private void setupViewPager() {

        //create the adapter for the view pager by getting values from the tab config.
        mainTabsPagerAdapter = new MainTabsPagerAdapter(getSupportFragmentManager(), getPagerFragments(), TabConfig.tabNames, TabConfig.NUMBER_OF_TABS);

        homeTabViewPager.setAdapter(mainTabsPagerAdapter);
    }

    private void setupTabs(){

        //set tab mode to scrollable.
        homeTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //setup the tab using the contents of the view pager.
        homeTabLayout.setupWithViewPager(homeTabViewPager);

    }

    /**
     * Get the fragments required for setting up the tabs using the fragment generator.
     * @return List of fragments.
     */
    private List<Fragment> getPagerFragments(){

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(FragmentGenerator.generateFragment(RepositoriesFragment.class));

        fragments.add(FragmentGenerator.generateFragment(CodeFragment.class));

        fragments.add(FragmentGenerator.generateFragment(IssuesFragment.class));

        fragments.add(FragmentGenerator.generateFragment(UsersFragment.class));

        return fragments;


    }
}
