package com.example.kitchenkompanion;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserItemAdapter extends ArrayAdapter<GroceryItem> {

    private Context mContext;
    int mResource;

    public UserItemAdapter(Context context, int resource, ArrayList<GroceryItem> list) {
        super(context, resource, list);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem (position).name;
        String units = getItem(position).units;
        float amount = getItem(position).amount;
        float minimumAmount = getItem(position).minimumAmount;
        String expirationDate = getItem(position).expirationDate;

        GroceryItem item = new GroceryItem(name, units, amount, minimumAmount);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.userItemName);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.userItemQuantity);
        TextView tvExpDate = (TextView) convertView.findViewById(R.id.userItemExpDate);

        tvName.setText(name);
        tvQuantity.setText(String.valueOf(amount) + " " + units);
        tvExpDate.setText("Expiration Date: " + expirationDate);

        return convertView;
    }
}
