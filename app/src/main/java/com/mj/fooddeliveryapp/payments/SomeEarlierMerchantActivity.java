package com.mj.fooddeliveryapp.payments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mj.fooddeliveryapp.Databackground;
import com.mj.fooddeliveryapp.R;
import com.mj.fooddeliveryapp.fragments.FoodAccount;
import com.mj.fooddeliveryapp.fragments.Menuu;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.mj.fooddeliveryapp.HomeAdapter.resname;
import static com.mj.fooddeliveryapp.MainActivity.fm;

public class SomeEarlierMerchantActivity extends AppCompatActivity implements PaymentResultListener {
public  Checkout checkout;
private String apikey="rzp_test_Co9x9bXWjcvd0K";
private RequestQueue mRequestQue;
private String URL = "https://fcm.googleapis.com/fcm/send";
public String fooditm;
public   int totalitem;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        checkout=new Checkout();
        checkout.setKeyID(apikey);
        Intent intent = getIntent();
       totalitem=intent.getIntExtra("totalitem",20);
       fooditm = intent.getStringExtra("fooditems");
        mRequestQue = Volley.newRequestQueue(this);
        dopayment(totalitem);
    }

    @Override
    public void onPaymentSuccess(String s) {
        try{
        Calendar cal = Calendar.getInstance();
        String dt1 = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
        SharedPreferences prefs=this.getSharedPreferences("MyPref",MODE_PRIVATE);
        String username =prefs.getString("key_name","0");
        String usermobile=prefs.getString("key_mobile","0");
        String useremail= prefs.getString("key_email","0");
        String userarea =prefs.getString("key_area","0");
        String usercity=prefs.getString("key_city","0");
        String userstate=prefs.getString("key_state","0");
        String userlatitude=prefs.getString("latitude","0");
        String userlongitude=prefs.getString("longitude","0");

        String last4=""+usermobile.charAt(6)+usermobile.charAt(7)+usermobile.charAt(8)+usermobile.charAt(9) + cal.get(Calendar.YEAR)+ cal.get(Calendar.MONTH)+ cal.get(Calendar.DAY_OF_MONTH)+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.SECOND);
        Databackground dt = new Databackground(this);
        dt.execute(last4,username,usermobile,useremail,userarea+","+usercity+","+userstate,userlatitude,userlongitude,resname,fooditm, String.valueOf(totalitem),"COD",dt1, "Not updated","0","Anonymous");
        sendNotification();
            fm.beginTransaction().replace(R.id.fragment_container,new Menuu()).commit();
        }
        catch (Exception e){Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "failure:"+s, Toast.LENGTH_SHORT).show();
    }

   public void dopayment(int totalitem)
    {
        try {
            SharedPreferences prefs=this.getSharedPreferences("MyPref",MODE_PRIVATE);
            String username =prefs.getString("key_name","0");
            String usermobile=prefs.getString("key_mobile","0");
            String useremail= prefs.getString("key_email","0");
            String userarea =prefs.getString("key_area","0");
            String usercity=prefs.getString("key_city","0");
            String userstate=prefs.getString("key_state","0");

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

                checkout.open(this, orderRequest);
           }
           else
               {
                Toast.makeText(this, "please complete your profile to proceed", Toast.LENGTH_SHORT).show();

            }
            // Order order = razorpay.Orders.create(orderRequest);
        } catch (JSONException e) {
            // Handle Exception
            System.out.println(e.toString());
        }
    }

    private void sendNotification() {

        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"yes");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","");
            notificationObj.put("body","any body");
            notificationObj.put("sound",R.raw.tmobile_wav);

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","puma");
            extraData.put("category","Shoes");

            json.put("notification",notificationObj);
            json.put("data",extraData);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR-------------------", "onResponse: "+response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError:------ "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAT1XhxNM:APA91bE2ybeRH_7-qoXs-YTBeTuZiiuvBMA7EcN7PvFgLnEYUOAPlwD5aWZmGgDwb1QWuncdWki5KxuN1Qao3v_uTFB5ygDWBCksXbxM1LtN4fWVfaJSeauawbsGl8rNCfW-U0N7f8zH");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            Toast.makeText(this,"89898989"+e.toString(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}