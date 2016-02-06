package com.vishnus1224.minigithub.config;

import android.support.v4.app.Fragment;

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

    public static final int NUMBER_OF_TABS = 4;

    public static final String[] tabNames = new String[]{
            "Repositories", "Code", "Issues", "Users"
    };


    /**
     * Generates a list of fragments for the pages of the view pager.
     * @return Fragment list.
     */
    public static List<Fragment> getPagerFragments(){

        List<Fragment> fragments = new ArrayList<>();



        return fragments;
    }
}
