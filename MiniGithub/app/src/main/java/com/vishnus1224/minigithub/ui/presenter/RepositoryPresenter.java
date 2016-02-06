package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.ui.view.BaseView;

import java.util.List;

/**
 * Handles operations related to repositories.
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoryPresenter implements Presenter {

    private BaseView view;

    @Override
    public void init(BaseView view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    /**
     * Fetch all repositories that match the given name.
     * @param repositoryName The name of the repository to search.
     */
    public void getRepositories(String repositoryName){


    }

    public interface RepositoryFetchListener {

        void onSuccess(List<Repository> repositories);

        void onFailure(String message);

    }
}
