package com.example.rynel.redditapi;

import com.example.rynel.redditapi.data.GsonRequest;
import com.example.rynel.redditapi.model.Child;
import com.example.rynel.redditapi.model.Reddit;

import java.util.List;

public interface MainActivityContract {

    interface View extends BaseView {

        void updateRecyclerView(List<Child> redditList);
        void addRequest(GsonRequest<Reddit> gsonRequest); //request from Reddit class
    }

    interface Presenter extends BasePresenter<View> {
        void makeRequest(String query);
    }
}