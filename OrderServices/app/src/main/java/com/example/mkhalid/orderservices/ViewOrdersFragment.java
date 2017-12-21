package com.example.mkhalid.orderservices;


import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ProgressBar;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.content.Intent;
/**
 * Created by mkhalid on 11/10/17.
 */

import java.util.ArrayList;

public class ViewOrdersFragment extends Fragment {

    public ArrayList<Order> postOrders = new ArrayList<Order>();

    public ViewOrdersFragment() {}

    private RelativeLayout loadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        String url = "https://order-services.herokuapp.com/api/v1/orders";

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        loadingView = rootView.findViewById(R.id.results_loading_view);
        loadingView.setVisibility(View.VISIBLE);

        OrderAsyncTask task = new OrderAsyncTask();
        task.execute(url);

        // Create a list of words
        //final ArrayList<Order> orders = new ArrayList<Order>();
        //orders.add(new Order("m.khalid999@gmail.com","iPad Pro 2017", 1, 599.99, "For educational purposes"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        OrderAdapter adapter = new OrderAdapter(getActivity(), postOrders);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to

                // Get the {@link Word} object at the given position the user clicked on
                Order order = postOrders.get(position);

                new AlertDialog.Builder(view.getContext())
                        .setTitle(order.getItem())
                        .setMessage("Email: " + order.getEmail() + "\n" + "Cost: $" + order.getCost() + "\n" + "Quantity: " + order.getQuantity() + "\n" + "Description: " + order.getDescription() + "\n")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                        .show();

            }
        });


        return rootView;
    }

    private class OrderAsyncTask extends AsyncTask<String, Void, ArrayList<Order>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected ArrayList<Order> doInBackground(String...strings) {
            return Utils.fetchNewsData(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Order> orders) {

            loadingView.setVisibility(View.GONE);
            postOrders = orders;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}


