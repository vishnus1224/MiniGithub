package com.vishnus1224.minigithub.di.module;

import com.vishnus1224.minigithub.di.scope.PerFragment;
import com.vishnus1224.minigithub.interactor.IssueInteractor;
import com.vishnus1224.minigithub.interactor.IssueInteractorImpl;
import com.vishnus1224.minigithub.interactor.RepositoryInteractor;
import com.vishnus1224.minigithub.interactor.RepositoryInteractorImpl;
import com.vishnus1224.minigithub.interactor.UserInteractor;
import com.vishnus1224.minigithub.interactor.UserInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vishnu on 4/20/2016.
 */
@Module
public class FragmentModule {

    @Provides @PerFragment
    RepositoryInteractor provideRepositoryInteractor(RepositoryInteractorImpl repositoryInteractorImpl){
        return repositoryInteractorImpl;
    }

    @Provides @PerFragment
    IssueInteractor provideIssueInteractor(IssueInteractorImpl issueInteractorImpl){
        return issueInteractorImpl;
    }

    @Provides @PerFragment
    UserInteractor provideUserInteractor(UserInteractorImpl userInteractorImpl){
        return userInteractorImpl;
    }

}
