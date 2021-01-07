package com.mj.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mj.fooddeliveryapp.fragments.FoodAccount;
import com.mj.fooddeliveryapp.fragments.FoodHome;
import com.mj.fooddeliveryapp.fragments.Foodcart;




public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottomBar);
        fm = getSupportFragmentManager();
        if(savedInstanceState==null)
        {
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