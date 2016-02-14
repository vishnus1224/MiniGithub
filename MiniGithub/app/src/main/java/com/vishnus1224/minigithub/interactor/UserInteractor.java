package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.model.User;

import java.util.List;

/**
 * Created by Vishnu on 2/14/2016.
 */
public interface UserInteractor {

    /**
     * Fetch list of users from the data store.
     * @param username Name of the user.
     * @param userInteractionListener Callback.
     */
    void fetchUsers(String username, UserInteractionListener userInteractionListener);

    /**
     * Load more users from the data store.
     * @param username The name of the user.
     * @param userInteractionListener Callback.
     */
    void loadMoreUsers(String username, UserInteractionListener userInteractionListener);


    interface UserInteractionListener{

        void onSuccess(List<User> users);

        void onFailure(String message);
    }
}
