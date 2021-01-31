package com.mj.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mj.fooddeliveryapp.fragments.FoodAccount;
import com.mj.fooddeliveryapp.fragments.FoodHome;
import com.mj.fooddeliveryapp.fragments.Foodcart;
import com.mj.fooddeliveryapp.payments.SomeEarlierMerchantActivity;

import static com.mj.fooddeliveryapp.fragments.FoodHome.restaurantlist;


public class MainActivity extends AppCompatActivity {
   public static BottomNavigationView bottomNavigation;
    public  static  FragmentManager fm;
    public static FrameLayout frameLayout;
    public  static TextView items;
    public  static LinearLayout placeorclear;
    public  static Button placeorder,clearorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottomBar);
        frameLayout=findViewById(R.id.gototcart);
        items=findViewById(R.id.items);
        placeorclear=findViewById(R.id.placeorderlayout);
        placeorder=findViewById(R.id.placeOrderBT);
        clearorder=findViewById(R.id.clearOrderBT);
        fm = getSupportFragmentManager();
        if(savedInstanceState==null)
        {
            restaurantbackground restaurantbackground=new restaurantbackground(this);
            restaurantbackground.execute();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FoodHome()).commit();

        }

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.food:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FoodHome()).commit();

                        return true;
                    case R.id.cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Foodcart()).commit();
                       // Intent intent =new Intent(MainActivity.this,SomeEarlierMerchantActivity.class);
                        //startActivity(intent);
                      //  SomeEarlierMerchantActivity someEarlierMerchantActivity=new SomeEarlierMerchantActivity(2,"7668693024","Nishkarsh","12", "modipuram","123","dominos");
                      //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Foodcart()).commit();
                        return true;
                    case R.id.account:


                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FoodAccount()).commit();


                        return true;
                }


                return false;
            }
        });


    }


}