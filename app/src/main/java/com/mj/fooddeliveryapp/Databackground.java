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



public class Databackground extends AsyncTask<String,Void,String> {
    Context context;
    private  String orderid,name,mobile,emailid,address,latitude,longitude,restaurant,foodmenu,billingprice,billingtype,deliverytime,pickuptime,deliverying,deliveryboyname;
    public Databackground(Context ctx)
    {
        context=ctx;

    }
    @Override
    protected String doInBackground(String... voids) {

        String login_url= "http://192.168.43.221/data.php";

        //  String login_url= "http://192.168.43.201/food/Accnt.php";
        try {
            orderid= voids[0];
            name=voids[1];
            mobile= voids[2];
            emailid=voids[3];
            address=voids[4];
            latitude=voids[5];
            longitude=voids[6];
            restaurant=voids[7];
            foodmenu=voids[8];
            billingprice=voids[9];
            billingtype=voids[10];
            deliverytime=voids[11];
            pickuptime=voids[12];
            deliverying=voids[13];
            deliveryboyname=voids[14];

            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("orderid","UTF-8")+"="+URLEncoder.encode(orderid,"UTF-8")+"&"
                    +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                    +URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"
                    +URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(emailid,"UTF-8")+"&"
                    +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                    +URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(latitude,"UTF-8")+"&"
                    +URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(longitude,"UTF-8")+"&"
                    +URLEncoder.encode("restaurant","UTF-8")+"="+URLEncoder.encode(restaurant,"UTF-8")+"&"
                    +URLEncoder.encode("foodmenu","UTF-8")+"="+URLEncoder.encode(foodmenu,"UTF-8")+"&"
                    +URLEncoder.encode("billingprice","UTF-8")+"="+URLEncoder.encode(billingprice,"UTF-8")+"&"
                    +URLEncoder.encode("billingtype","UTF-8")+"="+URLEncoder.encode(billingtype,"UTF-8")+"&"
                    +URLEncoder.encode("deliverytime","UTF-8")+"="+URLEncoder.encode(deliverytime,"UTF-8")+"&"
                    +URLEncoder.encode("pickuptime","UTF-8")+"="+URLEncoder.encode(pickuptime,"UTF-8")+"&"
                    +URLEncoder.encode("deliverying","UTF-8")+"="+URLEncoder.encode(deliverying,"UTF-8")+"&"
                    +URLEncoder.encode("deliveryboyname","UTF-8")+"="+URLEncoder.encode(deliveryboyname,"UTF-8");
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

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
