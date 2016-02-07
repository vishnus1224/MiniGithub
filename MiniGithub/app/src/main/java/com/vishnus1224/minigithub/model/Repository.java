package com.vishnus1224.minigithub.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class Repository {

    private int id;

    @SerializedName("full_name")
    private String name;

    private String description;

    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
