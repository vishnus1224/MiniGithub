package com.vishnus1224.minigithub.webservice;

import com.vishnus1224.minigithub.model.IssueContainer;
import com.vishnus1224.minigithub.model.RepositoryContainer;
import com.vishnus1224.minigithub.model.UserContainer;

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

    @Override
    public Call<IssueContainer> searchIssues(String issueName, int pageNumber, int resultsPerPage) {
        return githubService.searchIssues(issueName, pageNumber, resultsPerPage);
    }

    @Override
    public Call<UserContainer> searchUsers(String username, int pageNumber, int resultsPerPage) {
        return githubService.searchUsers(username, pageNumber, resultsPerPage);
    }
}
