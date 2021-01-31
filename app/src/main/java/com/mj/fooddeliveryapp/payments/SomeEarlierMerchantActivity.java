package com.mj.fooddeliveryapp.payments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mj.fooddeliveryapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SomeEarlierMerchantActivity extends AppCompatActivity implements PaymentResultListener {
public  Checkout checkout;
    // ...
private String apikey="rzp_test_Co9x9bXWjcvd0K";
private int amnt;
Button bt;
private  String number,name,address,orderid,restaurantid,restaurant;
/*public SomeEarlierMerchantActivity(int amnt,String number,String name,String orderid, String address,String restaurantid,String restaurant)
{
 this.amnt=amnt;
 this.number=number;
 this.name=name;
 this.orderid=orderid;
 this.address=address;
 this.restaurantid=restaurantid;
 this.restaurant=restaurant;
}*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

//        bt=findViewById(R.id.button3);
        checkout=new Checkout();
        checkout.setKeyID(apikey);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    JSONObject orderRequest = new JSONObject();
                    orderRequest.put("name", "Merchant Name");
                    orderRequest.put("description", "Reference No. #123456");
                    orderRequest.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                    orderRequest.put("currency", "INR");
                    orderRequest.put("amount", "100");//pass amount in currency subunits
                    orderRequest.put("prefill.email", "gaurav.kumar@example.com");
                    orderRequest.put("prefill.contact","9988776655");

                    checkout.open(SomeEarlierMerchantActivity.this,orderRequest);
                    // Order order = razorpay.Orders.create(orderRequest);
                } catch (JSONException e) {
                    // Handle Exception
                    System.out.println(e.toString());
                }
            }
        });


        // ...
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "failure:"+s, Toast.LENGTH_SHORT).show();
    }
}