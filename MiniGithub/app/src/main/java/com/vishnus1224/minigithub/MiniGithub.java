package com.vishnus1224.minigithub;

import android.app.Application;

import com.vishnus1224.minigithub.di.component.ApplicationComponent;
import com.vishnus1224.minigithub.di.component.DaggerApplicationComponent;
import com.vishnus1224.minigithub.di.component.DaggerNetComponent;
import com.vishnus1224.minigithub.di.component.NetComponent;
import com.vishnus1224.minigithub.di.module.ApplicationModule;
import com.vishnus1224.minigithub.di.module.NetModule;
import com.vishnus1224.minigithub.webservice.GithubService;

/**
 * Created by Vishnu on 4/22/2016.
 */
public class MiniGithub extends Application {

    private ApplicationComponent applicationComponent;

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        buildApplicationComponent();

        buildNetComponent();
    }


    private void buildApplicationComponent() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }


    private void buildNetComponent() {

        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(GithubService.BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}
