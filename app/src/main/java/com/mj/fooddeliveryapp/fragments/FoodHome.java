package com.mj.fooddeliveryapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mj.fooddeliveryapp.Foodmenu;
import com.mj.fooddeliveryapp.HomeAdapter;
import com.mj.fooddeliveryapp.R;
import com.mj.fooddeliveryapp.restaurantbackground;
import com.mj.fooddeliveryapp.restaurantmodelclass;

import java.util.ArrayList;
import java.util.List;


public class FoodHome extends Fragment {


    public RecyclerView recyclerView;
    public HomeAdapter homeAdapter;
    public List<restaurantmodelclass> foodmenuList;
    public static List<restaurantmodelclass> restaurantlist=new ArrayList<>();
    public static ProgressBar progressBar;
    androidx.appcompat.widget.SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage,container,false);
        progressBar=view.findViewById(R.id.searchprogress);
        recyclerView=view.findViewById(R.id.recyclerview);
        searchView=view.findViewById(R.id.searchbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //foodmenuList=new ArrayList<>();
        // foodmenuList.add(new restaurantmodelclass("Chowmein","",240+"",20,R.drawable.food1));
        //foodmenuList.add(new restaurantmodelclass("food item","",399+"",30,R.drawable.food2));
        //foodmenuList.add(new restaurantmodelclass("taste me tava","",129+"",20,R.drawable.food3));
        //foodmenuList.add(new restaurantmodelclass("Chowmein","",99+"",20,R.drawable.food1));
        //foodmenuList.add(new restaurantmodelclass("food item","",49+"",30,R.drawable.food2));
        //foodmenuList.add(new restaurantmodelclass("taste me tava","",49+"",20,R.drawable.food3));

        homeAdapter=new HomeAdapter(getActivity(),restaurantlist);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homeAdapter.getFilter().filter(newText);
                return false;
            }

        });


        return  view;

    }





}