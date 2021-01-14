package com.mj.fooddeliveryapp;



import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class writedata extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    boolean except =false;
    HttpURLConnection httpURLConnection;
    BufferedWriter bufferedWriter;
    OutputStream outputStream;
    InputStream inputStream;
    BufferedReader bufferedReader;
    String post_data;
    String result = "",type;
    String line = "";
    String login_url;
    URL url;
    View view;
    public String user_name,user_age,user_phone,user_trds;
    public writedata(Context ctx)
    {
        context=ctx;


    }
    @Override
    protected String doInBackground(String... voids) {
        type= voids[0];
        String login_url= "http://192.168.43.201/food/restaurant.php";
        // login_url= "http://192.168.43.151/family.php";
        if(true){
            try {

                url=new URL(login_url);
                httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                outputStream=httpURLConnection.getOutputStream();
                bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    post_data =
                            URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(voids[0], "UTF-8")
                                    + "&"
                                    + URLEncoder.encode("TSRDS_op_area", "UTF-8") + "=" + URLEncoder.encode(voids[1], "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                result = "";
                line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;


                }

                bufferedReader.close();
                inputStream.close();

                return  result;
            }catch (Exception e)
            {
                except=true;
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

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
