package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.model.UserContainer;
import com.vishnus1224.minigithub.webservice.RestApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishnu on 2/14/2016.
 */
public class UserInteractorImpl implements UserInteractor {

    private static final int RESULTS_PER_PAGE = 10;

    //The page number of the results page to be passed to the query.
    private int pageNumber = 1;

    //Flag for differentiating between normal fetch and loading more.
    //Used for decreasing the page number if an error occurs.
    private boolean loadMore = false;

    private UserInteractionListener userInteractionListener;

    private RestApi restApi;

    @Inject
    public UserInteractorImpl(RestApi restApi){
        this.restApi = restApi;
    }


    @Override
    public void fetchUsers(String username, UserInteractionListener userInteractionListener) {

        this.userInteractionListener = userInteractionListener;

        loadMore = false;

        pageNumber = 1;

        searchUsers(username);
    }

    @Override
    public void loadMoreUsers(String username, UserInteractionListener userInteractionListener) {

        this.userInteractionListener = userInteractionListener;

        incrementPageNumber(1);

        loadMore = true;

        searchUsers(username);
    }

    private void searchUsers(String username) {

        //get the webservice and make the search issues call
        Call<UserContainer> call = restApi.searchUsers(username, pageNumber, RESULTS_PER_PAGE);

        //fetch on a background thread and pass a callback.
        call.enqueue(callback);
    }


    private final Callback<UserContainer> callback = new Callback<UserContainer>() {

        @Override
        public void onResponse(Call<UserContainer> call, Response<UserContainer> response) {

            if(response.isSuccess()){

                //call the success method on the listener.
                if(userInteractionListener != null){

                    userInteractionListener.onSuccess(response.body().getUserList());

                }

            }else{

                checkLoadMoreCondition();

                if(userInteractionListener != null){

                    userInteractionListener.onFailure(response.message());

                }

            }
        }

        @Override
        public void onFailure(Call<UserContainer> call, Throwable t) {

            checkLoadMoreCondition();

            if(userInteractionListener != null){

                userInteractionListener.onFailure(t.getMessage());

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
