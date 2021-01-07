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

import java.util.ArrayList;
import java.util.List;


public class FoodHome extends Fragment {


    public RecyclerView recyclerView;
    public HomeAdapter homeAdapter;
    public List<Foodmenu> foodmenuList;
    public static ProgressBar progressBar;
    androidx.appcompat.widget.SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage,container,false);
        progressBar=view.findViewById(R.id.searchprogress);
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView=view.findViewById(R.id.searchbar);
        foodmenuList=new ArrayList<>();
         foodmenuList.add(new Foodmenu("Chowmein","",20,2,R.drawable.food1));
        foodmenuList.add(new Foodmenu("food item","",30,3,R.drawable.food2));
        foodmenuList.add(new Foodmenu("taste me tava","",20,2,R.drawable.food3));
        foodmenuList.add(new Foodmenu("Chowmein","",20,2,R.drawable.food1));
        foodmenuList.add(new Foodmenu("food item","",30,3,R.drawable.food2));
        foodmenuList.add(new Foodmenu("taste me tava","",20,2,R.drawable.food3));
        homeAdapter=new HomeAdapter(getActivity(),foodmenuList);
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