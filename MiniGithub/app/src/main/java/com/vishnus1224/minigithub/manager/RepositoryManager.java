package com.vishnus1224.minigithub.manager;

import com.vishnus1224.minigithub.generator.WebServiceGenerator;
import com.vishnus1224.minigithub.webservice.GithubService;

/**
 * Created by Vishnu on 2/7/2016.
 */
public final class RepositoryManager {

    private RepositoryManager(){

    }

    //Singleton instance for the web service.
    private static GithubService githubService;


    //If github service is not initialized then create a new instance else return the existing instance.
    public static GithubService getWebService(){

        if(githubService == null){

            githubService = createGithubService();
        }

        return githubService;
    }

    //Create the desired webservice using the web service generator.
    private static GithubService createGithubService() {

        return WebServiceGenerator.createWebService(GithubService.class, GithubService.BASE_URL);

    }

}
