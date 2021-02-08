package com.mj.fooddeliveryapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.mj.fooddeliveryapp.fragments.Menuu;

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
import java.util.ArrayList;
import java.util.List;

import static com.mj.fooddeliveryapp.MainActivity.fm;
import static com.mj.fooddeliveryapp.fragments.FoodHome.restaurantlist;


public class FoodmenuBackground extends AsyncTask<String,Void,String> {
    Context context;

    public static List<Foodmenu> foodmenuList=new ArrayList<Foodmenu>();
    private  String foodname,foodtype,price;
    int resid ;
    public FoodmenuBackground(Context ctx)
    {
        context=ctx;

    }
    @Override
    protected String doInBackground(String... voids) {

       String login_url= "http://192.168.43.221/retrieve.php";
      //  String login_url= "http://192.168.43.201/food/retrieve2.php";
        try {


            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("resid","UTF-8")+"="+URLEncoder.encode(voids[0],"UTF-8");
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
       // Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        try {
            String foodname="";
            String foodtype="";
            String price="";
            int resid=0;
            JSONArray jsonArray = new JSONArray(result);

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject obj=jsonArray.getJSONObject(i);

                if(obj.getString("resid")==null)
                    resid=0;else resid=obj.getInt("resid");

                foodmenuList.add(new Foodmenu(obj.getString("foodname"),obj.getString("price"),obj.getString("foodtype")));

               // Toast.makeText(context,obj.getString("foodname"),Toast.LENGTH_SHORT).show();
            }
          //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Menuu()).commit();
            //FragmentManager fragmentManager=context.getApplicationContext().get
            fm.beginTransaction().replace(R.id.fragment_container,new Menuu()).commit();
//context.getApplicationContext().getSupp
        }catch (Exception e){

            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
