package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.model.Repository;

import java.util.List;

/**
 * Created by Vishnu on 2/7/2016.
 */
public interface RepositoryInteractor {

    void fetchRepositories(String repositoryName, RepositoryInteractionListener repositoryInteractionListener);

    interface RepositoryInteractionListener{

        void onSuccess(List<Repository> repositoryList);

        void onFailure(String message);

    }

}
