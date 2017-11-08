package com.example.rynel.redditapi;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void detachView();
}