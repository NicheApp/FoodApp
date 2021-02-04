package com.mj.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ImageViewHolder> implements Filterable {
    public Context mContext;
    public List<restaurantmodelclass> mUploads;
    public List<restaurantmodelclass> mUploadscopy;

    public HomeAdapter(Context context, List<restaurantmodelclass> uploads) {

        mContext = context;

        mUploads = uploads;
        mUploadscopy = new ArrayList<>(mUploads);

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurantcard, parent, false);


        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        restaurantmodelclass uploadCurrent = mUploads.get(position);
        holder.foodname.setText(uploadCurrent.getRestaurantname());
        holder.foodtime.setText(" • "+uploadCurrent.getDistance()+"mins");
        holder.fooddistance.setText(" • "+uploadCurrent.getTime()+"kms");
        Picasso.get()
                .load(uploadCurrent.getRestaurantimage())
                .into(holder.foodimage);
        //  holder.foodimage.setBackgroundResource(R.drawable.resbar);
//       holder.cardView.setOnClickListener((View.OnClickListener) mContext);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, position+"", Toast.LENGTH_SHORT).show();

              FoodmenuBackground foodmenuBackground =new FoodmenuBackground(mContext);
                foodmenuBackground.execute(uploadCurrent.getResid()+"");
            }
        });


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView foodname;
        public ImageView foodimage;
        public TextView fooddistance;
        public  TextView foodtime;
        public CardView cardView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            foodname = itemView.findViewById(R.id.restaurantNameTV);
            foodimage= itemView.findViewById(R.id.shopIconIV);
            fooddistance= itemView.findViewById(R.id.distance);
            foodtime=itemView.findViewById(R.id.time);
            cardView=itemView.findViewById(R.id.rescard);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "fsd", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

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
                for (restaurantmodelclass item : mUploadscopy){
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
    };

}