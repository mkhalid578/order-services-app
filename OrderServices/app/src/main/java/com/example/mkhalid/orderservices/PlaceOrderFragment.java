package com.example.mkhalid.orderservices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mkhalid on 11/10/17.
 */

public class PlaceOrderFragment extends Fragment {

    public PlaceOrderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.place_order, container, false);

        EditText itemName = (EditText) rootView.findViewById(R.id.item);


        Button btn = (Button) rootView.findViewById(R.id.placeOrder);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                Toast.makeText(getActivity(), "Order placed", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

}
