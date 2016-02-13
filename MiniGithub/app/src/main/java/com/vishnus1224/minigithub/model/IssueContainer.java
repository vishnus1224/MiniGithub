package com.vishnus1224.minigithub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu on 2/13/2016.
 */
public class IssueContainer {

    @SerializedName("items")
    private List<Issue> issueList;

    public List<Issue> getIssueList(){
        return issueList;
    }
}
