package com.vishnus1224.minigithub.listener;

import com.vishnus1224.minigithub.interactor.UserInteractor;
import com.vishnus1224.minigithub.model.User;
import com.vishnus1224.minigithub.ui.presenter.UserPresenter;

import java.util.List;

/**
 * Created by Vishnu on 2/14/2016.
 */
public class LoadMoreUsersListener implements UserInteractor.UserInteractionListener {

    private UserPresenter userPresenter;

    public LoadMoreUsersListener(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public void onSuccess(List<User> users) {

        if(userPresenter != null){

            userPresenter.loadMoreUsersSuccess(users);

        }

    }

    @Override
    public void onFailure(String message) {

        if(userPresenter != null){

            userPresenter.loadMoreUsersFailed(message);

        }

    }
}
