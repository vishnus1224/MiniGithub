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

    @SerializedName("html_url")
    private String htmlURL;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("watchers_count")
    private int watchersCount;

    @SerializedName("open_issues_count")
    private int openIssuesCount;

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

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }
}
