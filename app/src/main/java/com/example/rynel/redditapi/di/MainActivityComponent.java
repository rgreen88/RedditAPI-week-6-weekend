package com.example.rynel.redditapi.di;

import com.example.rynel.redditapi.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}