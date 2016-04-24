package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.manager.WebServiceManager;
import com.vishnus1224.minigithub.model.IssueContainer;
import com.vishnus1224.minigithub.webservice.RestApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private RestApi restApi;

    @Inject
    public IssueInteractorImpl(RestApi restApi){
        this.restApi = restApi;
    }


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

        //get the webservice and make the search issues call
        Call<IssueContainer> call = restApi.searchIssues(issueName, pageNumber, RESULTS_PER_PAGE);

        //fetch on a background thread and pass a callback.
        call.enqueue(callback);

    }

    private Callback<IssueContainer> callback = new Callback<IssueContainer>() {
        @Override
        public void onResponse(Call<IssueContainer> call, Response<IssueContainer> response) {

            if(response.isSuccess()){

                //call the success method on the listener.
                if(issueInteractionListener != null){

                    issueInteractionListener.onSuccess(response.body().getIssueList());

                }

            }else{

                checkLoadMoreCondition();

                if(issueInteractionListener != null){

                    issueInteractionListener.onFailure(response.message());

                }

            }

        }

        @Override
        public void onFailure(Call<IssueContainer> call, Throwable t) {

            checkLoadMoreCondition();

            if(issueInteractionListener != null){

                issueInteractionListener.onFailure(t.getMessage());

            }

        }
    };


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
