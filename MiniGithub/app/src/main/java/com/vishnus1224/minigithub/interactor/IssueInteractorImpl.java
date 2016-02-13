package com.vishnus1224.minigithub.interactor;

/**
 * Created by Vishnu on 2/13/2016.
 */
public class IssueInteractorImpl implements IssueInteractor {

    private static final int RESULTS_PER_PAGE = 10;

    //The page number of the results page to be passed to the query.
    private int pageNumber = 1;

    //Flag for differentiating between normal fetch and loading more.
    //Used for decreasing the page number if an error occurs.
    private boolean loadMore = false;

    private IssueInteractionListener issueInteractionListener;


    @Override
    public void fetchIssues(String issueName, IssueInteractionListener issueInteractionListener) {

        this.issueInteractionListener = issueInteractionListener;

        loadMore = false;

        pageNumber = 1;

        searchIssues(issueName);

    }

    @Override
    public void loadMoreIssues(String issueName, IssueInteractionListener issueInteractionListener) {

        this.issueInteractionListener = issueInteractionListener;

        incrementPageNumber(1);

        loadMore = true;

        searchIssues(issueName);

    }

    private void searchIssues(String issueName) {

    }


    //decrease the page number by 1 if load more is true.
    private void checkLoadMoreCondition() {

        if(loadMore){

            loadMore = false;

            decrementPageNumber(1);

        }
    }

    private void incrementPageNumber(int amount){

        pageNumber += amount;

    }

    private void decrementPageNumber(int amount){

        pageNumber -= amount;

    }

}
