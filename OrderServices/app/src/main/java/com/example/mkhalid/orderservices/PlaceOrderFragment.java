package com.example.mkhalid.orderservices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by mkhalid on 11/10/17.
 */

public class PlaceOrderFragment extends Fragment {

    public PlaceOrderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.place_order, container, false);

        return rootView;
    }
}
