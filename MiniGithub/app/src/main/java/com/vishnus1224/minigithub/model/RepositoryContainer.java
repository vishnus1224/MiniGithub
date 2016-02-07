package com.vishnus1224.minigithub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu on 2/7/2016.
 */
public class RepositoryContainer {

    @SerializedName("items")
    private List<Repository> repositories;

    public List<Repository> getRepositories(){
        return repositories;
    }
}
