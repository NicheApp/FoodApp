package com.mj.fooddeliveryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.developer.kalert.KAlertDialog;
import com.mj.fooddeliveryapp.fragments.FoodAccount;
import com.mj.fooddeliveryapp.fragments.Foodcart;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.mj.fooddeliveryapp.MainActivity.bottomNavigation;
import static com.mj.fooddeliveryapp.MainActivity.fm;
import static com.mj.fooddeliveryapp.MainActivity.frameLayout;
import static com.mj.fooddeliveryapp.MainActivity.items;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ImageViewHolder> {
    public Context mContext;
    public List<Foodmenu> mUploads;
    public List<Foodmenu> mUploadscopy;
   public static HashMap<Integer , cartmodel> hm=new HashMap<>();
   public  static List<cartmodel> cart=new ArrayList<>();
    Activity activity;


    public MenuAdapter(Context context, List<Foodmenu> uploads,Activity activity) {

        mContext = context;

        mUploads = uploads;
        mUploadscopy = new ArrayList<>(mUploads);
        this.activity=activity;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restmenucard, parent, false);
        return new ImageViewHolder(v);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        Foodmenu uploadCurrent = mUploads.get(position);
        holder.foodname.setText(uploadCurrent.getFoodname());
        holder.price.setText("₹ "+uploadCurrent.getPrice());
        /*Picasso.get()
                .load(uploadCurrent.getRestaurantimage())
               .fit().centerCrop()
                .into(holder.foodimage);*/
        SharedPreferences prefs=activity.getSharedPreferences("MyPref",MODE_PRIVATE);
        holder.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext,position+"hjhj",Toast.LENGTH_SHORT).show();
                Log.i("---------",position+"");
                if(prefs.contains("key_name") && prefs.contains("key_mobile") && prefs.contains("key_email")  && prefs.contains("key_area") &&prefs.contains("key_city")&&prefs.contains("key_state"))
                {
                    holder.addimage.setVisibility(View.GONE);
                    holder.numberButtonENB.setNumber(Integer.toString(1));
                    holder.numberButtonENB.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);
                    bottomNavigation.setVisibility(View.GONE);

                    hm.put(position,new cartmodel(uploadCurrent.getFoodname(),Integer.parseInt(uploadCurrent.getPrice()),"",1,uploadCurrent.getFoodtype()));
                    items.setText(" "+hm.size()+" items");
                }
                else {
                    new KAlertDialog(mContext, KAlertDialog.WARNING_TYPE)
                            .setTitleText("Incomplete Profile")
                            .setContentText("Please complete your profile!")
                            .setConfirmText("Go To Account!")
                            .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                @Override
                                public void onClick(KAlertDialog sDialog) {
                                   //
                                    sDialog.dismissWithAnimation();
                                    fm.beginTransaction().replace(R.id.fragment_container,new FoodAccount()).commit();

                                }
                            })
                            .show();

                }

            }
        });
        holder.numberButtonENB.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
          //  Log.i("---------",position+"");
            if(prefs.contains("key_name") && prefs.contains("key_mobile") && prefs.contains("key_email")  && prefs.contains("key_area") &&prefs.contains("key_city")&&prefs.contains("key_state"))
            {

                if(holder.numberButtonENB.getNumber().equals(Integer.toString(0))) {
                    holder.numberButtonENB.setVisibility(View.GONE);
                    holder.addimage.setVisibility(View.VISIBLE);

                    hm.remove(position);
                    items.setText(" "+hm.size()+" items");
                }
                else
                {

                    hm.replace(position,new cartmodel(uploadCurrent.getFoodname(),Integer.parseInt(uploadCurrent.getPrice()),"",Integer.parseInt(holder.numberButtonENB.getNumber()),uploadCurrent.getFoodtype()));
                    items.setText(" "+hm.size()+" items");
                }

            }
            else {
                new KAlertDialog(mContext, KAlertDialog.WARNING_TYPE)
                        .setTitleText("Incomplete Profile")
                        .setContentText("Please complete your profile!")
                        .setConfirmText("Go To Account!")
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog sDialog) {
                                //
                                sDialog.dismissWithAnimation();
                                fm.beginTransaction().replace(R.id.fragment_container,new FoodAccount()).commit();

                            }
                        })
                        .show();

            }
        });

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm.beginTransaction().replace(R.id.fragment_container,new Foodcart()).commit();

            }
        });


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
        public ImageView lineimage;
        public ElegantNumberButton numberButtonENB;
        public ImageViewHolder(View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.dishname);
            tagimage= itemView.findViewById(R.id.tag);
            price= itemView.findViewById(R.id.price);
            addimage=itemView.findViewById(R.id.ad);
            lineimage=itemView.findViewById(R.id.line);
            numberButtonENB = itemView.findViewById(R.id.numberButtonENB);
            numberButtonENB.setRange(0, 10);
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

