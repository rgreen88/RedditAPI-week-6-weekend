package com.example.rynel.redditapi;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.rynel.redditapi.data.GsonRequest;
import com.example.rynel.redditapi.model.Reddit;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    public static final String BASE_URL = "https://www.reddit.com/r/"; //reddit site r/ default load
    private static final String TAG = "Presenter";

    MainActivityContract.View view;

    public MainActivityPresenter() {
    }

    @Override
    public void attachView(MainActivityContract.View view) {
        Log.d(TAG, "attachView: ");
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void makeRequest(String query) {
        String url = BASE_URL + query + "/.json";

        //GsonRequest using Reddit class from JSON pojo
        GsonRequest<Reddit> gsonRequest = new GsonRequest<>(url, Reddit.class, null,
                new Response.Listener<Reddit>() {

                    @Override
                    public void onResponse(Reddit response) {
                        view.updateRecyclerView( response.getData().getChildren() );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error){
                        Log.e(TAG, "onErrorResponse: ", error);
                    }
                });

        view.addRequest(gsonRequest);
    }
}