package com.example.rynel.redditapi.data;

/**
 * Created by rynel on 10/16/2017.
 */

import com.example.rynel.redditapi.model.Reddit;
import com.example.rynel.redditapi.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.rynel.redditapi.util.Constants.VALUES.APP_ID;

/**
 * Created by rynel on 10/13/2017.
 */

public interface RemoteService {

    @GET("/api/v1/authorize.compact?"  + Constants.VALUES.API_KEY + APP_ID)
    Observable<Reddit> getQuery(@Query("q") String query,
                                 @Query("from") int start,
                                 @Query("to") int to);


}
