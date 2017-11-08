package com.example.rynel.redditapi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.rynel.redditapi.data.GsonRequest;
import com.example.rynel.redditapi.di.DaggerMainActivityComponent;
import com.example.rynel.redditapi.model.Child;
import com.example.rynel.redditapi.model.Reddit;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivity";

    //butter knife
    @BindView(R.id.rvQueryList)
    RecyclerView rvQueryList;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    //dagger mvp presenter object
    @Inject
    MainActivityPresenter presenter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue queue;

    //list array
    private List<Child> childList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //butter knife bind

        //injecting presenter
        DaggerMainActivityComponent.create().inject(this);
        queue = Volley.newRequestQueue(this);  //<--------------volley
        initRecyclerView();
        presenter.attachView(this);
        presenter.makeRequest("funny");
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        rvQueryList.setLayoutManager(layoutManager);
        rvQueryList.setAdapter(new MyItemListAdapter(childList));
    }


    public void addRequest(GsonRequest<Reddit> gsonRequest) {
        queue.add(gsonRequest);
    }

    public void updateRecyclerView(List<Child> list) {
        Log.d(TAG, "updateRecyclerView: adding " + list.size() + " items to the Recycler View.");
        childList.addAll(list);
        rvQueryList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

    }
}