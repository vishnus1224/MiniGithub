package com.vishnus1224.minigithub.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vishnu on 4/22/2016.
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides @Singleton
    Application provideApplication(){
        return application;
    }
}
