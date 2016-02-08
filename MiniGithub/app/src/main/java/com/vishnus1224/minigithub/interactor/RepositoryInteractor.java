package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.model.Repository;

import java.util.List;

/**
 * Created by Vishnu on 2/7/2016.
 */
public interface RepositoryInteractor {

    /**
     * Executed when user searches for a repository the 1st time.
     * @param repositoryName The name of the repository.
     * @param repositoryInteractionListener Callback.
     */
    void fetchRepositories(String repositoryName, RepositoryInteractionListener repositoryInteractionListener);

    /**
     * Executed when user clicks the load more button.
     * @param repositoryName The name of the repository.
     * @param repositoryInteractionListener Callback.
     */
    void loadMoreRepositories(String repositoryName, RepositoryInteractionListener repositoryInteractionListener);

    interface RepositoryInteractionListener{

        void onSuccess(List<Repository> repositoryList);

        void onFailure(String message);

    }

}
