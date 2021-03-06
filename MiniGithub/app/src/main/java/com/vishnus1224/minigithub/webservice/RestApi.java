package com.vishnus1224.minigithub.webservice;

import com.vishnus1224.minigithub.model.IssueContainer;
import com.vishnus1224.minigithub.model.RepositoryContainer;
import com.vishnus1224.minigithub.model.UserContainer;

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

    /**
     * Search for issues on Github.
     * @param issueName Name of the issue.
     * @param pageNumber Page number of the results page.
     * @param resultsPerPage Total number of results to be fetched.
     * @return Instance of Call<IssueContainer> to be enqueued.
     */
    Call<IssueContainer> searchIssues(String issueName, int pageNumber, int resultsPerPage);

    /**
     * Search for github users.
     * @param username Username of the account to search for.
     * @param pageNumber Page number of the results page.
     * @param resultsPerPage Total number of results to be fetched.
     * @return Instance of Call<UserContainer> to be enqueued.
     */
    Call<UserContainer> searchUsers(String username, int pageNumber, int resultsPerPage);
}
