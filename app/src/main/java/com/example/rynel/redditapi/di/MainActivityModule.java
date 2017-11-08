package com.example.rynel.redditapi.di;


import com.example.rynel.redditapi.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

//denote the module with @module annotation
@Module
public class MainActivityModule {

    //method that provides the mainActivityPresenter object
    @Provides
    MainActivityPresenter providesMainActivityPresenter () {
        return new MainActivityPresenter();
    }
}