package com.vishnus1224.minigithub.ui.presenter;

import android.text.TextUtils;

import com.vishnus1224.minigithub.interactor.RepositoryInteractor;
import com.vishnus1224.minigithub.interactor.RepositoryInteractorImpl;
import com.vishnus1224.minigithub.listener.FetchRepositoriesListener;
import com.vishnus1224.minigithub.listener.LoadMoreRepositoriesListener;
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

    private RepositoryInteractor repositoryInteractor = new RepositoryInteractorImpl();

    private List<Repository> repositories;

    //flag to indicate that search is currently going on.
    private boolean searchInProgress;

    //flag to check if load more request is in progress.
    private boolean loadingMore;

    //The keyword that was searched before the current one. Search will not take place if current and this keyword is same.
    //This will be used to know if the current keyword and the last one was same.
    //If they are different than the repository list will be cleared.
    private String lastSearchKeyword = "";

    //listener for getting callback when repository fetch is completed.
    private FetchRepositoriesListener fetchRepositoriesListener;

    //listener for getting callback when load more repository call is completed.
    private LoadMoreRepositoriesListener loadMoreRepositoriesListener;

    @Override
    public void init(BaseView view) {

        if(!(view instanceof RepositoryView)){
            throw new RuntimeException("View must be of type RepositoryView");
        }

        this.view = (RepositoryView) view;

        fetchRepositoriesListener = new FetchRepositoriesListener(this);

        loadMoreRepositoriesListener = new LoadMoreRepositoriesListener(this);
    }

    @Override
    public void resume() {

        //hide the no content view is repositories are not empty.
        if(!repositories.isEmpty()){

            view.hideNoContentView();

            view.addFooterView();

        }

        if(searchInProgress){

            view.hideNoContentView();

            view.showProgress();

        }

        if(loadingMore){

            view.showFooterProgress();

            view.hideLoadMoreButton();

        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        fetchRepositoriesListener = null;

        loadMoreRepositoriesListener = null;

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
        if(searchInProgress || loadingMore){

            view.showError("Search is in progress");

            return;
        }

        //if the last searched keyword and the new one are the same and repositories are not empty, then do not search.
        if(keywordsAreSame(repositoryName) && !repositories.isEmpty()){

            view.showError("Results are shown for the current search query");

            view.addFooterView();

            return;
        }

        //if the last search keyword is empty and repositories are not empty,
        //then user has navigated away from the fragment and come back to it.
        if(TextUtils.isEmpty(lastSearchKeyword) && !repositories.isEmpty()){

            view.hideNoContentView();

            view.addFooterView();

            //update the last search keyword.
            lastSearchKeyword = repositoryName;

            return;
        }

        //if this point is reached then set the last keyword to the one passed in.
        lastSearchKeyword = repositoryName;

        searchInProgress = true;

        view.hideNoContentView();

        view.showProgress();

        repositoryInteractor.fetchRepositories(repositoryName, fetchRepositoriesListener);

    }


    private boolean keywordsAreSame(String repositoryName) {

        return lastSearchKeyword.equals(repositoryName);

    }

    //Fetch repositories successful.
    public void repositoryFetchSuccess(List<Repository> repositories){

        searchInProgress = false;

        view.hideProgress();

        if(repositories.isEmpty()){

            //tell the view that there are no more results.
            view.showNoContentView();

        }else {

            this.repositories.clear();

            this.repositories.addAll(repositories);

            //hide the no content text view.
            view.hideNoContentView();

            //show the new repositories in the view.
            view.showRepositories();

        }
    }

    //Failed to fetch repositories.
    public void repositoryFetchFailure(String message){

        searchInProgress = false;

        view.hideProgress();

        view.showError(message);

        if(repositories.isEmpty()) {
            view.showNoContentView();
        }

    }

    /**
     * Loads more repositories for this keyword.
     */
    public void loadMoreRepositories() {

        if(searchInProgress){

            view.showError("Search is in progress");

            return;

        }

        view.hideLoadMoreButton();

        view.showFooterProgress();

        loadingMore = true;

        repositoryInteractor.loadMoreRepositories(lastSearchKeyword, loadMoreRepositoriesListener);

    }

    /**
     * Successfully fetched more repositories.
     * @param repositoryList List of repositories fetched.
     */
    public void loadMoreRepositoriesSuccess(List<Repository> repositoryList){

        loadingMore = false;

        if(repositoryList.isEmpty()){

            view.showError("No more repositories found");

        }else{

            repositories.addAll(repositoryList);

            view.showRepositories();

        }

        view.hideFooterProgress();

        view.showLoadMoreButton();
    }

    //Failed to fetch repositories.
    public void loadMoreRepositoriesFailure(String message){

        loadingMore = false;

        view.showError(message);

        view.hideFooterProgress();

        view.showLoadMoreButton();

    }

}
