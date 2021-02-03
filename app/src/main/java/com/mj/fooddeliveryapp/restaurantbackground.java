package com.mj.fooddeliveryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import static com.mj.fooddeliveryapp.fragments.FoodHome.restaurantlist;

public class restaurantbackground extends AsyncTask<String,Void,String> {
    Context context;

    public restaurantbackground(Context ctx)
    {
        context=ctx;


    }
    @Override
    protected String doInBackground(String... voids) {
        String login_url= "http://192.168.43.221/retrieve1.php";
      //  String login_url= "http://192.168.43.201/food/retrieve.php";
        if(true){
            try {


                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));


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
        return null;
    }
    @Override
    protected void onPreExecute() {
        // alertDialog=new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        try {
            int time=0;
            double distance=0;
            int rating=0,resid=0;
            JSONArray jsonArray = new JSONArray(result);

             for(int i=0;i<jsonArray.length();i++)
             {
                 JSONObject obj=jsonArray.getJSONObject(i);
                 if(obj.getString("time")==null)
                  time =0;else time= obj.getInt("time");
                 if(obj.getString("distance")==null)
                     distance=0;else distance=obj.getDouble("distance");
                  if(obj.getString("resid")==null)
                      resid=0;else resid=obj.getInt("resid");


                 restaurantlist.add(new restaurantmodelclass(obj.getString("name"),obj.getString("resimage"),
                         time,distance,resid,5));


             }

        }catch (Exception e){

            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

