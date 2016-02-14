package com.vishnus1224.minigithub.webservice;

import com.vishnus1224.minigithub.model.IssueContainer;
import com.vishnus1224.minigithub.model.RepositoryContainer;
import com.vishnus1224.minigithub.model.UserContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface GithubService {

    String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<RepositoryContainer> fetchRepositories(@Query("q") String repositoryName,@Query("page") int pageNumber, @Query("per_page") int resultsPerPage);

    @GET("search/issues")
    Call<IssueContainer> searchIssues(@Query("q") String issueName, @Query("page") int pageNumber, @Query("per_page") int resultsPerPage);

    @GET("search/users")
    Call<UserContainer> searchUsers(@Query("q") String username, @Query("page") int pageNumber, @Query("per_page") int resultsPerPage);
}
