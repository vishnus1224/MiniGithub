package com.vishnus1224.minigithub.interactor;

import com.vishnus1224.minigithub.manager.RepositoryManager;
import com.vishnus1224.minigithub.model.Repository;

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
        Call<List<Repository>> call = RepositoryManager.getWebService().fetchRepositories(repositoryName);

        //fetch on a background thread and pass a callback.
        call.enqueue(callback);

    }

    private Callback<List<Repository>> callback = new Callback<List<Repository>>() {
        @Override
        public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

            if(response.isSuccess()){

                //call the success method on the listener.
                repositoryInteractionListener.onSuccess(response.body());

            }else{

                repositoryInteractionListener.onFailure(response.message());
            }

        }

        @Override
        public void onFailure(Call<List<Repository>> call, Throwable t) {

            repositoryInteractionListener.onFailure(t.getMessage());
        }
    };
}
