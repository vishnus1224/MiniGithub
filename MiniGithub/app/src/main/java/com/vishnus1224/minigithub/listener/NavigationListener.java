package com.vishnus1224.minigithub.listener;

import com.vishnus1224.minigithub.model.Repository;

/**
 * Listener for passing events from fragments to the activity.
 * Created by Vishnu on 2/13/2016.
 */
public interface NavigationListener {

    void navigateToRepositoryDetail(Repository repository);
}
