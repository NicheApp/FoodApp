package com.mj.fooddeliveryapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mj.fooddeliveryapp.R;
import com.mj.fooddeliveryapp.cartmodel;
import com.mj.fooddeliveryapp.restaurantbackground;
import com.mj.fooddeliveryapp.writedata;
//import com.razorpay.Checkout;
//import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;

public class Foodcart extends Fragment {
List<com.mj.fooddeliveryapp.cartmodel> cart;

public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);
        cart=new ArrayList<>();
       cart.add(new cartmodel("Biryani",199,"Balaji chicken restaurant"));
      final ListView listview = (ListView) view.findViewById(R.id.cartLV);
   // Adapter adapter=new ArrayAdapter<>()


   return  view;
    }



}
