package com.example.rynel.redditapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    final TextView mTextView = (TextView) findViewById(R.id.text);

    public static final String TAG = "MyTag";
    RequestQueue mRequestQueue;  // Assume this exists.

    // Instantiate the cache
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

    // Set up the network to use HttpURLConnection as the HTTP client.
    Network network = new BasicNetwork(new HurlStack());


    TextView mTxtDisplay;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind
        mTxtDisplay = findViewById(R.id.txtDisplay);


        // Add the request to the RequestQueue. <---- sends a request with add()
        queue.add(stringRequest);

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();
    }

    // Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://www.reddit.com/api/";

    // Formulate the string request from the provided URL and handle response.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    mTextView.setText(String.format("%s%s", getString(R.string.response),
                            response.substring(0, 500)));
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //handle error
            mTextView.setText(R.string.error);
        }
    });

    //stops request service made by mRequestQueue
    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

}
