package com.vishnus1224.minigithub.config;

/**
 * Utility class for holding the configuration parameters for the tabs.
 * Created by Vishnu on 2/6/2016.
 */
public final class TabConfig {

    //prevent instantiation.
    private TabConfig(){

    }

    //Total number of tabs.
    public static final int NUMBER_OF_TABS = 3;

    //Names for the tabs.
    public static final String[] tabNames = new String[]{
            "Repositories", "Issues", "Users"
    };


}
