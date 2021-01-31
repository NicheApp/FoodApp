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

public class Foodcart extends Fragment implements PaymentResultListener{

    cartitemadapter  cartitemadapter;
    TextView cartitemtotal,deliveryfee, totaltopayl;
    private String apikey="rzp_test_Co9x9bXWjcvd0K";
    public  Checkout checkout;
    private int totalitem=0;
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);
        cartitemtotal=view.findViewById(R.id.itemCostTV);
        deliveryfee=view.findViewById(R.id.delFeeTV);
        totaltopayl=view.findViewById(R.id.totalPayTV);
        placeorclear.setVisibility(View.VISIBLE);
    checkout=new Checkout();
    checkout.setKeyID(apikey);
       //bottomNavigation.setVisibility(View.VISIBLE);
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
                 try {
                     SharedPreferences prefs=getActivity().getSharedPreferences("MyPref",MODE_PRIVATE);
                     String username =prefs.getString("key_name","0");
                     String usermobile=prefs.getString("key_mobile","0");
                     String useremail= prefs.getString("key_email","0");
                     String userarea =prefs.getString("key_name","0");
                     String usercity=prefs.getString("key_area","0");
                     String userstate=prefs.getString("key_city","0");

                     if(!(username.equals("0")) && !(usermobile.equals("0"))
                             &&!(useremail.equals("0"))&& !(userarea.equals("0")) && !(usercity.equals("0")&& !(userstate.equals("0"))))
                     {
                         JSONObject orderRequest = new JSONObject();
                         orderRequest.put("name", username);
                         orderRequest.put("description", userarea+","+usercity+","+userstate);
                         orderRequest.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                         orderRequest.put("currency", "INR");
                         orderRequest.put("amount", totalitem*100);//pass amount in currency subunits
                         orderRequest.put("prefill.email", useremail);
                         orderRequest.put("prefill.contact", usermobile);

                         checkout.open(getActivity(), orderRequest);
                     }
                     else
                     {
                         Toast.makeText(getContext(), "please complete your profile to proceed", Toast.LENGTH_SHORT).show();

                     }
                     // Order order = razorpay.Orders.create(orderRequest);
                 } catch (JSONException e) {
                     // Handle Exception
                     System.out.println(e.toString());
                 }

             }
         });
         clearorder.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {



             }
         });
   // Adapter adapter=new ArrayAdapter<>()


   return  view;
    }


    @Override
    public void onPaymentSuccess(String s) {
        placeorclear.setVisibility(View.GONE);
        bottomNavigation.setVisibility(View.VISIBLE);
    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FoodHome()).commit();
    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}
