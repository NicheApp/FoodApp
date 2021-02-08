package com.mj.fooddeliveryapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class SigninBackground extends AsyncTask<String,Void,String> {
    Context context;

    private  String name,mobile,area,city,state,email;
    SigninBackground(Context ctx)
    {
        context=ctx;

    }
    @Override
    protected String doInBackground(String... voids) {
       // String login_url= "http://192.168.43.201/food/registration.php";
        String login_url= "http://192.168.43.221/registration.php";
        try {
            name= voids[0];
            mobile=voids[1];
            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                    +URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line=bufferedReader.readLine())!=null)
            {
                result+=line;


            }
            bufferedReader.close();
            inputStream.close();
            return  result;
        }catch (Exception e)
        {
            return e.toString();
        }

    }
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        restaurantbackground restaurantbackground=new restaurantbackground(context);
        restaurantbackground.execute();
        Intent intent = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
