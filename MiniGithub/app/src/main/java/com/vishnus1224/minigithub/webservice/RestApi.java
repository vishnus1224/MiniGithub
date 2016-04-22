package com.vishnus1224.minigithub.webservice;

import com.vishnus1224.minigithub.model.RepositoryContainer;

import retrofit2.Call;

/**
 * Created by Vishnu on 4/22/2016.
 */
public interface RestApi {

    /**
     * Fetch repositories from the server.
     * @param repositoryName The name of the repository.
     * @param pageNumber Page number of the results page.
     * @param resultsPerPage Total number of results to be fetched.
     * @return Instance of Call<RepositoryContainer> to be enqueued.
     */
    Call<RepositoryContainer> fetchRepositories(String repositoryName, int pageNumber, int resultsPerPage);
}
