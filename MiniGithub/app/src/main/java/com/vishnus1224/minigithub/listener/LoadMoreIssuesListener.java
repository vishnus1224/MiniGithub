package com.vishnus1224.minigithub.listener;

import com.vishnus1224.minigithub.interactor.IssueInteractor;
import com.vishnus1224.minigithub.model.Issue;
import com.vishnus1224.minigithub.ui.presenter.IssuePresenter;

import java.util.List;

/**
 * Created by Vishnu on 2/13/2016.
 */
public class LoadMoreIssuesListener implements IssueInteractor.IssueInteractionListener {

    private IssuePresenter issuePresenter;

    public LoadMoreIssuesListener(IssuePresenter issuePresenter) {
        this.issuePresenter = issuePresenter;
    }

    @Override
    public void onSuccess(List<Issue> issueList) {

        if(issuePresenter != null){

            issuePresenter.loadMoreIssuesSuccess(issueList);

        }

    }

    @Override
    public void onFailure(String message) {

        if(issuePresenter != null){

            issuePresenter.loadMoreIssuesFailure(message);

        }

    }
}
