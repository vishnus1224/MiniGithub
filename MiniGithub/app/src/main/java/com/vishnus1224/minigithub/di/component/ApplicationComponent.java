package com.vishnus1224.minigithub.di.component;

import android.app.Application;

import com.vishnus1224.minigithub.MiniGithub;
import com.vishnus1224.minigithub.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vishnu on 4/22/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MiniGithub miniGithub);

}
