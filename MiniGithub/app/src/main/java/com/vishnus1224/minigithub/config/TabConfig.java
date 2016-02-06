package com.vishnus1224.minigithub.config;

import android.support.v4.app.Fragment;

import com.vishnus1224.minigithub.generator.FragmentGenerator;
import com.vishnus1224.minigithub.ui.fragment.CodeFragment;
import com.vishnus1224.minigithub.ui.fragment.IssuesFragment;
import com.vishnus1224.minigithub.ui.fragment.RepositoriesFragment;
import com.vishnus1224.minigithub.ui.fragment.UsersFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for holding the configuration parameters for the tabs.
 * Created by Vishnu on 2/6/2016.
 */
public final class TabConfig {

    //prevent instantiation.
    private TabConfig(){

    }

    //Total number of tabs.
    public static final int NUMBER_OF_TABS = 4;

    //Names for the tabs.
    public static final String[] tabNames = new String[]{
            "Repositories", "Code", "Issues", "Users"
    };


    /**
     * Generates a list of fragments for the pages of the view pager.
     * @return Fragment list.
     */
    public static List<Fragment> getPagerFragments(){

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(FragmentGenerator.generateFragment(RepositoriesFragment.class));

        fragments.add(FragmentGenerator.generateFragment(CodeFragment.class));

        fragments.add(FragmentGenerator.generateFragment(IssuesFragment.class));

        fragments.add(FragmentGenerator.generateFragment(UsersFragment.class));

        return fragments;
    }
}
