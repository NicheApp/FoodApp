package com.mj.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Signin extends AppCompatActivity {
    EditText name,mobile;
    Button proceed;
    TextView welcome;
    ImageView fodapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.cnt);
        fodapp = findViewById(R.id.imageView5);
        proceed = findViewById(R.id.button2);
        welcome = findViewById(R.id.textView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=name.getText().toString();
                String usermobile=mobile.getText().toString();
                SigninBackground signinBackground= new SigninBackground(getApplicationContext());
                signinBackground.execute(username,usermobile);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", username);
                editor.putString("key_mobile", usermobile);
                editor.commit();
            }
        });

    }
}