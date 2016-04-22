package com.vishnus1224.minigithub.webservice;

import com.vishnus1224.minigithub.model.RepositoryContainer;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Vishnu on 4/22/2016.
 */
public class RestApiImpl implements RestApi {

    private GithubService githubService;

    @Inject
    public RestApiImpl(Retrofit retrofit){

        githubService = retrofit.create(GithubService.class);
    }

    @Override
    public Call<RepositoryContainer> fetchRepositories(String repositoryName, int pageNumber, int resultsPerPage) {
        return githubService.fetchRepositories(repositoryName, pageNumber, resultsPerPage);
    }
}
