package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.manager.RepositoryManager;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.model.RepositoryContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishnu on 2/7/2016.
 */
public class RepositoryInteractorImpl implements RepositoryInteractor {

    private static final int RESULTS_PER_PAGE = 10;

    //The page number of the results page to be passed to the query.
    private int pageNumber = 1;

    private RepositoryInteractionListener repositoryInteractionListener;

    //Flag for differentiating between normal fetch and loading more.
    //Used for decreasing the page number if an error occurs.
    private boolean loadMore = false;

    @Override
    public void fetchRepositories(String repositoryName, RepositoryInteractionListener repositoryInteractionListener) {

        this.repositoryInteractionListener = repositoryInteractionListener;

        loadMore = false;

        //reset the page number to 1.
        pageNumber = 1;

        fetchRepositories(repositoryName);
    }

    @Override
    public void loadMoreRepositories(String repositoryName, RepositoryInteractionListener repositoryInteractionListener) {

        this.repositoryInteractionListener = repositoryInteractionListener;

        incrementPageNumber(1);

        loadMore = true;

        fetchRepositories(repositoryName);

    }

    private void fetchRepositories(String repositoryName){

        //get the webservice and make the fetch repositories call
        Call<RepositoryContainer> call = RepositoryManager.getWebService().fetchRepositories(repositoryName, pageNumber, RESULTS_PER_PAGE);

        //fetch on a background thread and pass a callback.
        call.enqueue(callback);

    }

    private final Callback<RepositoryContainer> callback = new Callback<RepositoryContainer>() {
        @Override
        public void onResponse(Call<RepositoryContainer> call, Response<RepositoryContainer> response) {

            if(response.isSuccess()){

                //call the success method on the listener.
                if(repositoryInteractionListener != null) {
                    repositoryInteractionListener.onSuccess(response.body().getRepositories());
                }

            }else{

                checkLoadMoreCondition();

                if(repositoryInteractionListener != null) {
                    repositoryInteractionListener.onFailure(response.message());
                }
            }

        }

        @Override
        public void onFailure(Call<RepositoryContainer> call, Throwable t) {

            checkLoadMoreCondition();

            if(repositoryInteractionListener != null) {
                repositoryInteractionListener.onFailure(t.getMessage());
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
