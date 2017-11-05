package com.example.rynel.redditapi.data;

/**
 * Created by rynel on 10/16/2017.
 */

import com.example.rynel.redditapi.model.Reddit;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rynel on 10/13/2017.
 */

public class RemoteDataSource {

    //Reddit URL
    public static final String BASE_URL = "https://www.reddit.com/api/";

    //Retrofit construct
    public static Retrofit create() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Observable<Reddit> getData(String query, int start, int to ) {
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create( RemoteService.class );

        return remoteService.getQuery( query, start, to );
    }
}

