package com.mj.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ImageViewHolder> {
    public Context mContext;
    public List<Foodmenu> mUploads;
    public List<Foodmenu> mUploadscopy;

    public MenuAdapter(Context context, List<Foodmenu> uploads) {

        mContext = context;

        mUploads = uploads;
        mUploadscopy = new ArrayList<>(mUploads);

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restmenucard, parent, false);
        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        Foodmenu uploadCurrent = mUploads.get(position);
        holder.foodname.setText(uploadCurrent.getFoodname());
        holder.price.setText("₹ "+uploadCurrent.getPrice());
        /*Picasso.get()
                .load(uploadCurrent.getRestaurantimage())
               .fit().centerCrop()
                .into(holder.foodimage);*/


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView foodname;
        public ImageView tagimage;
        public TextView price;
public ImageButton addimage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.dishname);
            tagimage= itemView.findViewById(R.id.tag);
            price= itemView.findViewById(R.id.price);
            addimage=itemView.findViewById(R.id.ad);

        }
    }
/*
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<restaurantmodelclass> filteredList = new ArrayList<>();
            if(constraint ==null || constraint.length()== 0){
                filteredList.addAll(mUploadscopy);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MenuModelclassitem : mUploadscopy){
                    if(item.getRestaurantname().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mUploads.clear();
            mUploads.addAll((List)results.values);
            notifyDataSetChanged();
        }
        */
    };

