package com.mj.fooddeliveryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
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
    private static  final int REQUEST_LOCATION=1;

    LocationManager locationManager;
   public String latitude,longitude;
    EditText name,mobile;
    Button proceed;
    TextView welcome;
    ImageView fodapp;
    String latlong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        SharedPreferences prefs = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        if (prefs.contains("key_mobile")) {
            startActivity(new Intent(this,MainActivity.class));
        } else
        {
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
                String username = name.getText().toString();
                String usermobile = mobile.getText().toString();
                SigninBackground signinBackground = new SigninBackground(getApplicationContext());
                signinBackground.execute(username, usermobile);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", username);
                editor.putString("key_mobile", usermobile);
                editor.commit();

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //Check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then

                    getLocation();
                }
            }
        });

    }
    }
    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(Signin.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Signin.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("latitude", latitude);
                editor.putString("longitude", longitude);
                editor.commit();
                Toast.makeText(getApplicationContext(),latitude,Toast.LENGTH_LONG).show();
               // showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("latitude", latitude);
                editor.putString("longitude", longitude);
                editor.commit();
                Toast.makeText(getApplicationContext(),latitude,Toast.LENGTH_LONG).show();
             //   showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("latitude", latitude);
                editor.putString("longitude", longitude);
                editor.commit();
                Toast.makeText(getApplicationContext(),latitude,Toast.LENGTH_LONG).show();
               // showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }
    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}