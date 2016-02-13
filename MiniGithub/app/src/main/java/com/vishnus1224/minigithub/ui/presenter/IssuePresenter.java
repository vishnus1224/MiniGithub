package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.model.Issue;
import com.vishnus1224.minigithub.ui.view.BaseView;
import com.vishnus1224.minigithub.ui.view.IssueView;
import com.vishnus1224.minigithub.utility.Utils;

import java.util.List;

/**
 * Created by Vishnu on 2/13/2016.
 */
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

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    @Override
    public void init(BaseView view) {

        if(!(view instanceof IssueView)){
            throw new RuntimeException("View must be of type IssueView");
        }

        issueView = (IssueView) view;

    }

    @Override
    public void resume() {

        //hide the no content view is issue list is not empty.
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

                issueView.showError("Results are shown for the current search query");

                issueView.addFooterView();

            }

            return;
        }



        //set the current issue name.
        currentIssueName = issueName;

        searchInProgress = true;

        issueView.hideNoContentView();

        issueView.showProgress();


    }
}
