package com.mj.fooddeliveryapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.mj.fooddeliveryapp.cartitemadapter;
import com.mj.fooddeliveryapp.cartmodel;
import com.mj.fooddeliveryapp.payments.SomeEarlierMerchantActivity;
import com.mj.fooddeliveryapp.restaurantbackground;
import com.mj.fooddeliveryapp.writedata;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;
import static android.content.Context.MODE_PRIVATE;
import static com.mj.fooddeliveryapp.MainActivity.bottomNavigation;
import static com.mj.fooddeliveryapp.MainActivity.clearorder;
import static com.mj.fooddeliveryapp.MainActivity.placeorclear;
import static com.mj.fooddeliveryapp.MainActivity.placeorder;
import static com.mj.fooddeliveryapp.MenuAdapter.cart;
import static com.mj.fooddeliveryapp.MenuAdapter.hm;

public class Foodcart extends Fragment {

    cartitemadapter  cartitemadapter;
    TextView cartitemtotal,deliveryfee, totaltopayl;
    private int totalitem=0;
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);
        cartitemtotal=view.findViewById(R.id.itemCostTV);
        deliveryfee=view.findViewById(R.id.delFeeTV);
        totaltopayl=view.findViewById(R.id.totalPayTV);
        placeorclear.setVisibility(View.VISIBLE);
        ListView fooditemlist = (ListView) view.findViewById(R.id.cartLV);

    for (int i : hm.keySet()) {

        cart.add(hm.get(i));

    }
    cartitemadapter= new cartitemadapter(getContext(), R.layout.order_items, cart);
    fooditemlist.setAdapter(cartitemadapter);
    int delivery=10;
    for(int i=0;i<cart.size();i++)
        totalitem+=(cart.get(i).getFoodprice())*(cart.get(i).getTimes());
         cartitemtotal.setText("₹"+totalitem);
         deliveryfee.setText("₹"+delivery);
         totalitem+=delivery;
         totaltopayl.setText("₹"+totalitem);
          SharedPreferences prefs=getActivity().getSharedPreferences("MyPref",MODE_PRIVATE);
         placeorder.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(getContext(),SomeEarlierMerchantActivity.class);
                 intent.putExtra("totalitem",totalitem);
                 startActivity(intent);
             }
         });
         clearorder.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {



             }
         });



   return  view;
    }

}
