package com.vishnus1224.minigithub.di.component;

import com.vishnus1224.minigithub.di.module.FragmentModule;
import com.vishnus1224.minigithub.di.scope.PerFragment;
import com.vishnus1224.minigithub.ui.fragment.IssueFragment;
import com.vishnus1224.minigithub.ui.fragment.RepositoryFragment;
import com.vishnus1224.minigithub.ui.fragment.UserFragment;

import dagger.Component;

/**
 * Created by Vishnu on 4/20/2016.
 */
@PerFragment
@Component(dependencies = NetComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(RepositoryFragment repositoryFragment);

    void inject(IssueFragment issueFragment);

    void inject(UserFragment userFragment);
}
