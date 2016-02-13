package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.model.Issue;

import java.util.List;

/**
 * Created by Vishnu on 2/13/2016.
 */
public interface IssueInteractor {

    /**
     * Fetch list of issues from the data store.
     * @param issueName Name of the issue.
     * @param issueInteractionListener Callback.
     */
    void fetchIssues(String issueName, IssueInteractionListener issueInteractionListener);

    /**
     * Load more issues from the data store.
     * @param issueName The name of the issue.
     * @param issueInteractionListener Callback.
     */
    void loadMoreIssues(String issueName, IssueInteractionListener issueInteractionListener);

    public interface IssueInteractionListener{

        void onSuccess(List<Issue> issueList);

        void onFailure(String message);
    }
}
