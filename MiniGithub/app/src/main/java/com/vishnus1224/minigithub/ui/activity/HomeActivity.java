package com.vishnus1224.minigithub.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.config.TabConfig;
import com.vishnus1224.minigithub.generator.FragmentGenerator;
import com.vishnus1224.minigithub.ui.adapter.MainTabsPagerAdapter;
import com.vishnus1224.minigithub.ui.fragment.BaseFragment;
import com.vishnus1224.minigithub.ui.fragment.CodeFragment;
import com.vishnus1224.minigithub.ui.fragment.IssueFragment;
import com.vishnus1224.minigithub.ui.fragment.RepositoryFragment;
import com.vishnus1224.minigithub.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

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

        setTabListener();

        handleSearchIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleSearchIntent(intent);
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

    private void setTabListener() {

        //set a listener on the tab to detect clicks.
        homeTabLayout.setOnTabSelectedListener(this);

        //set the 1st tab as the default one.
        onTabSelected(homeTabLayout.getTabAt(0));

    }


    private void handleSearchIntent(Intent intent) {

        //check to see if the search button was clicked.
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){

            //get the keyword entered by the user.
            String keyword = intent.getStringExtra(SearchManager.QUERY);
        }

    }


    /**
     * Get the fragments required for setting up the tabs using the fragment generator.
     * @return List of fragments.
     */
    private List<Fragment> getPagerFragments(){

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(FragmentGenerator.generateFragment(RepositoryFragment.class));

        fragments.add(FragmentGenerator.generateFragment(CodeFragment.class));

        fragments.add(FragmentGenerator.generateFragment(IssueFragment.class));

        fragments.add(FragmentGenerator.generateFragment(UserFragment.class));

        return fragments;


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int tabIndex = tab.getPosition();

        BaseFragment fragment = (BaseFragment) mainTabsPagerAdapter.getItem(tabIndex);

        fragment.fetchData(null);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.home_menu, menu);

        //Associate the searchable info with the search view.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.homeActionSearch).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
