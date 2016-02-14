package com.vishnus1224.minigithub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu on 2/14/2016.
 */
public class UserContainer {

    @SerializedName("items")
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }
}
