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

    private RepositoryInteractionListener repositoryInteractionListener;

    @Override
    public void fetchRepositories(String repositoryName, RepositoryInteractionListener repositoryInteractionListener) {

        this.repositoryInteractionListener = repositoryInteractionListener;

        //get the webservice and make the fetch repositories call
        Call<RepositoryContainer> call = RepositoryManager.getWebService().fetchRepositories(repositoryName, 1, 10);

        //fetch on a background thread and pass a callback.
        call.enqueue(callback);

    }

    private Callback<RepositoryContainer> callback = new Callback<RepositoryContainer>() {
        @Override
        public void onResponse(Call<RepositoryContainer> call, Response<RepositoryContainer> response) {

            if(response.isSuccess()){

                //call the success method on the listener.
                repositoryInteractionListener.onSuccess(response.body().getRepositories());

            }else{

                repositoryInteractionListener.onFailure(response.message());
            }

        }

        @Override
        public void onFailure(Call<RepositoryContainer> call, Throwable t) {

            repositoryInteractionListener.onFailure(t.getMessage());
        }
    };
}
