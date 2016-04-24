package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.di.scope.PerFragment;
import com.vishnus1224.minigithub.interactor.IssueInteractor;
import com.vishnus1224.minigithub.interactor.IssueInteractorImpl;
import com.vishnus1224.minigithub.listener.FetchIssuesListener;
import com.vishnus1224.minigithub.listener.LoadMoreIssuesListener;
import com.vishnus1224.minigithub.model.Issue;
import com.vishnus1224.minigithub.ui.view.BaseView;
import com.vishnus1224.minigithub.ui.view.IssueView;
import com.vishnus1224.minigithub.utility.Utils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 2/13/2016.
 */
@PerFragment
public class IssuePresenter implements Presenter {

    private IssueView issueView;

    private List<Issue> issueList;

    //flag to indicate that search is currently going on.
    private boolean searchInProgress;

    //flag to check if load more request is in progress.
    private boolean loadingMore;

    //The keyword that was searched before the current one. Search will not take place if current and this keyword is same.
    //This will be used to know if the current keyword and the last one was same.
    private String lastSearchKeyword = "";

    //Name of the issue being searched.
    private String currentIssueName;

    private IssueInteractor issueInteractor;

    //listener for getting callback when issue fetch is completed.
    private FetchIssuesListener fetchIssuesListener;

    //listener for getting callback when load more issues call is completed.
    private LoadMoreIssuesListener loadMoreIssuesListener;

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    @Inject
    public IssuePresenter(IssueInteractor issueInteractor){
        this.issueInteractor = issueInteractor;
    }

    @Override
    public void init(BaseView view) {

        if(!(view instanceof IssueView)){
            throw new RuntimeException("View must be of type IssueView");
        }

        issueView = (IssueView) view;

        fetchIssuesListener = new FetchIssuesListener(this);

        loadMoreIssuesListener = new LoadMoreIssuesListener(this);

    }

    @Override
    public void resume() {

        //hide the no content view if issue list is not empty.
        if(!issueList.isEmpty()){

            issueView.hideNoContentView();

            issueView.addFooterView();

        }

        if(searchInProgress){

            issueView.hideNoContentView();

            issueView.showProgress();

        }

        if(loadingMore){

            issueView.showFooterProgress();

            issueView.hideLoadMoreButton();

        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        fetchIssuesListener = null;

        loadMoreIssuesListener = null;
    }

    //Search for the specified issue name.
    public void searchIssues(String issueName){

        //if search is in progress, then notify view and return.
        if(searchInProgress || loadingMore){

            issueView.showError("Search is in progress");

            return;
        }

        //if the last searched keyword and the new one are same.
        if(Utils.areStringsEqual(lastSearchKeyword, issueName)){

            if(issueList.isEmpty()){

                issueView.showNoContentView();

            }else{

                //if issue list is not empty, then do not search.

                issueView.addFooterView();

            }

            return;
        }



        //set the current issue name.
        currentIssueName = issueName;

        searchInProgress = true;

        issueView.hideNoContentView();

        issueView.showProgress();

        issueInteractor.fetchIssues(issueName, fetchIssuesListener);

    }

    //load more issues for the current search keyword.
    public void loadMoreIssues(){

        if(searchInProgress){

            issueView.showError("Search is in progress");

            return;

        }

        issueView.hideLoadMoreButton();

        issueView.showFooterProgress();

        loadingMore = true;

        issueInteractor.loadMoreIssues(lastSearchKeyword, loadMoreIssuesListener);

    }

    //Fetched issues successfully.
    public void issueSearchSuccess(List<Issue> issues){

        searchInProgress = false;

        lastSearchKeyword = currentIssueName;

        issueView.hideProgress();

        issueList.clear();

        if(issues.isEmpty()){

            issueView.showIssues();

            issueView.removeFooterView();

            //tell the view that there are no more results.
            issueView.showNoContentView();

        }else {

            issueList.addAll(issues);

            //hide the no content text view.
            issueView.hideNoContentView();

            //show the new issues in the view.
            issueView.showIssues();

            issueView.addFooterView();

        }
    }

    //Failed to fetch issues.
    public void issueSearchFailed(String message){

        searchInProgress = false;

        issueView.hideProgress();

        issueView.showError(message);

        if(issueList.isEmpty()) {
            issueView.showNoContentView();
        }
    }

    //successfully loaded more issues for the current search term.
    public void loadMoreIssuesSuccess(List<Issue> issues){

        loadingMore = false;

        if(issues.isEmpty()){

            issueView.showError("No more issues found");

        }else{

            issueList.addAll(issues);

            issueView.showIssues();

        }

        issueView.hideFooterProgress();

        issueView.showLoadMoreButton();
    }

    //failed to load more issues for the current keyword.
    public void loadMoreIssuesFailure(String message){

        loadingMore = false;

        issueView.showError(message);

        issueView.hideFooterProgress();

        issueView.showLoadMoreButton();
    }
}
