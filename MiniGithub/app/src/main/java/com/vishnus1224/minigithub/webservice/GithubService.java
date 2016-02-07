package com.vishnus1224.minigithub.webservice;

import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.model.RepositoryContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface GithubService {

    String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<RepositoryContainer> fetchRepositories(@Query("q") String repositoryName,@Query("page") int pageNumber, @Query("per_page") int resultsPerPage);
}
