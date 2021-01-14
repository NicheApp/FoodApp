package com.mj.fooddeliveryapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mj.fooddeliveryapp.R;
import com.mj.fooddeliveryapp.restaurantbackground;
import com.mj.fooddeliveryapp.writedata;

import java.nio.file.Watchable;

public class Foodcart extends Fragment {
TextView tv;
Button bt;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cartview, container, false);
        tv= view.findViewById(R.id.textView);
bt=view.findViewById(R.id.button);
bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        writedata writedata=new writedata(getContext());
        writedata.execute(tv.getText().toString());
    }
});

   return  view;
    }


}
