package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.interactor.RepositoryInteractor;
import com.vishnus1224.minigithub.interactor.RepositoryInteractorImpl;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.ui.view.BaseView;
import com.vishnus1224.minigithub.ui.view.RepositoryView;

import java.util.List;

/**
 * Handles operations related to repositories.
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoryPresenter implements Presenter {

    private RepositoryView view;

    private RepositoryInteractor repositoryInteractor;

    private List<Repository> repositories;

    //flag to indicate that search is currently going on.
    private boolean searchInProgress;

    @Override
    public void init(BaseView view) {

        if(!(view instanceof RepositoryView)){
            throw new RuntimeException("View must be of type RepositoryView");
        }

        this.view = (RepositoryView) view;

        repositoryInteractor = new RepositoryInteractorImpl();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        searchInProgress = false;

        repositoryInteractor = null;

        view = null;

    }

    public void setRepositories(List<Repository> repositories){

        this.repositories = repositories;

    }

    /**
     * Fetch all repositories that match the given name.
     * @param repositoryName The name of the repository to search.
     */
    public void searchRepositories(String repositoryName){

        //if search is in progress, then notify view and return.
        if(searchInProgress){

            view.showError("Search currently in progress");

            return;
        }

        view.hideNoContentView();

        view.showProgress();

        repositoryInteractor.fetchRepositories(repositoryName, repositoryInteractionListener);

    }

    private RepositoryInteractor.RepositoryInteractionListener repositoryInteractionListener = new RepositoryInteractor.RepositoryInteractionListener() {
        @Override
        public void onSuccess(List<Repository> repositoryList) {

            searchInProgress = false;

            view.hideProgress();

            if(repositoryList.isEmpty()){

                //tell the view that there are no more results.
                view.noMoreRepositories();

            }else {

                //hide the no content text view.
                view.hideNoContentView();

                //show the new repositories in the view.
                view.showRepositories(repositoryList);

            }

        }

        @Override
        public void onFailure(String message) {

            searchInProgress = false;

            view.hideProgress();

            view.showError(message);

            view.noMoreRepositories();
        }
    };
}
