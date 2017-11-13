package com.example.mkhalid.orderservices;

/**
 * Created by mkhalid on 11/12/17.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


public class OrderLoader extends AsyncTaskLoader<List<Order>> {

    /** Tag for log messages */
    private static final String LOG_TAG = OrderLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link OrderLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public OrderLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Order> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news articles.
        List<Order> orders = Utils.fetchNewsData(mUrl);

        return orders;
    }
}