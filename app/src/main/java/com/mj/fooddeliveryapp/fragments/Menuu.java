package com.mj.fooddeliveryapp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mj.fooddeliveryapp.AccntBackground;
import com.mj.fooddeliveryapp.Foodmenu;
import com.mj.fooddeliveryapp.FoodmenuBackground;
import com.mj.fooddeliveryapp.MenuAdapter;
import com.mj.fooddeliveryapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.mj.fooddeliveryapp.FoodmenuBackground.foodmenuList;


public class Menuu extends Fragment {
    public RecyclerView recyclerView;
    public MenuAdapter menuAdapter;
    public static ProgressBar progressBar;
    public   Activity activity;

    androidx.appcompat.widget.SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restmenu,container,false);
        progressBar=view.findViewById(R.id.accsearchprogress);
        recyclerView=view.findViewById(R.id.accrecyclerview);
        searchView=view.findViewById(R.id.accsearchbar);

        activity=getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

     //   FoodmenuBackground foodmenuBackground= new FoodmenuBackground(getContext());
      //  foodmenuBackground.execute();

     /*   foodmenuList=new ArrayList<>();
        foodmenuList.add(new com.mj.fooddeliveryapp.Foodmenu("Chowmein",290,"v"));
        foodmenuList.add(new com.mj.fooddeliveryapp.Foodmenu("Momoz",290,"v"));
        foodmenuList.add(new com.mj.fooddeliveryapp.Foodmenu("Pizza",290,"v"));
        foodmenuList.add(new com.mj.fooddeliveryapp.Foodmenu("Burger",290,"v"));
        foodmenuList.add(new com.mj.fooddeliveryapp.Foodmenu("Pasta",290,"v"));
*/

       menuAdapter= new MenuAdapter(getActivity(),foodmenuList,activity);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    /*    searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                menuAdapter.getFilter().filter(newText);
                return false;
            }

        });

*/
        return  view;

    }
}