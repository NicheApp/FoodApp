package com.mj.fooddeliveryapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class cartitemadapter extends ArrayAdapter<cartmodel> {

    Context context;
    int resource;
    List<cartmodel> objects = null;

    public cartitemadapter(Context context, int resource, List<cartmodel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setObjects(List<cartmodel> objects) {
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final cartmodel orderItemModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_items, parent, false);
        }
        TextView eachItemCostTV = convertView.findViewById(R.id.eachItemCostTV);
        TextView itemNameTV = convertView.findViewById(R.id.itemNameTV);
        if (orderItemModel.getFoodtype().equals("n")) {
            Drawable img = getContext().getResources().getDrawable(R.drawable.nonveg);
            itemNameTV.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        }
        itemNameTV.setText(" " + orderItemModel.getFoodname() + " X " + orderItemModel.getTimes());
        eachItemCostTV.setText("₹" + orderItemModel.getFoodprice());
        return convertView;
    }
}


