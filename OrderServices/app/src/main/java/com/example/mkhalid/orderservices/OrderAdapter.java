package com.example.mkhalid.orderservices;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by mkhalid on 11/10/17.
 */

public class OrderAdapter extends ArrayAdapter<Order> {

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Order currentOrder = getItem(position);

        TextView emailTextView = (TextView) listItemView.findViewById(R.id.email);

        emailTextView.setText(currentOrder.getEmail());

        TextView itemTextView = (TextView) listItemView.findViewById(R.id.itemName);

        itemTextView.setText(currentOrder.getItem());

        TextView quantityTextView = (TextView) listItemView.findViewById(R.id.quantity);

        quantityTextView.setText(String.valueOf(currentOrder.getQuantity()));

        return listItemView;
    }
}
