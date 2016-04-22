package com.vishnus1224.minigithub.di.component;

import com.vishnus1224.minigithub.MiniGithub;
import com.vishnus1224.minigithub.di.module.NetModule;
import com.vishnus1224.minigithub.webservice.RestApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vishnu on 4/22/2016.
 */
@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {

    //exposed to dependent components.
    RestApi restApi();

    void inject(MiniGithub miniGithub);
}
