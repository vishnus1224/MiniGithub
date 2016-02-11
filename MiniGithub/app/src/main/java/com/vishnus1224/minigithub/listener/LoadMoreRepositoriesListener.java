package com.vishnus1224.minigithub.listener;

import com.vishnus1224.minigithub.interactor.RepositoryInteractor;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.ui.presenter.RepositoryPresenter;

import java.util.List;

/**
 * Created by Vishnu on 2/11/2016.
 */
public class LoadMoreRepositoriesListener implements RepositoryInteractor.RepositoryInteractionListener {

    private RepositoryPresenter repositoryPresenter;

    public LoadMoreRepositoriesListener(RepositoryPresenter repositoryPresenter){

        this.repositoryPresenter = repositoryPresenter;

    }


    @Override
    public void onSuccess(List<Repository> repositoryList) {

        if(repositoryPresenter != null){

            repositoryPresenter.loadMoreRepositoriesSuccess(repositoryList);

        }

    }

    @Override
    public void onFailure(String message) {

        if(repositoryPresenter != null){

            repositoryPresenter.loadMoreRepositoriesFailure(message);

        }

    }
}
