package com.mj.fooddeliveryapp.fragments;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mj.fooddeliveryapp.AccntBackground;
import com.mj.fooddeliveryapp.R;
import com.mj.fooddeliveryapp.SigninBackground;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

//Hellp therre again merge again
public class FoodAccount  extends Fragment {
EditText name,mb,email,area,city,state;
ImageView mb1,email1,area1,city1,state1;
Button save;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account, container, false);
           name = view.findViewById(R.id.name);
           mb = view.findViewById(R.id.mb);
           email = view.findViewById(R.id.ed);
           area = view.findViewById(R.id.area);
           city = view.findViewById(R.id.city);
           state = view.findViewById(R.id.state);
           save = view.findViewById(R.id.save);

        mb1 = view.findViewById(R.id.imageView);
        email1 = view.findViewById(R.id.imageView1);
        area1 = view.findViewById(R.id.imageView2);
        city1 = view.findViewById(R.id.imageView3);
        state1 = view.findViewById(R.id.imageView4);



        SharedPreferences prefs=getActivity().getSharedPreferences("MyPref",MODE_PRIVATE);
        name.setText(prefs.getString("key_name", "name"));
        mb.setText(prefs.getString("key_mobile", "mobile"));
        email.setText(prefs.getString("key_email", "email"));
        area.setText(prefs.getString("key_area", "area"));
        city.setText(prefs.getString("key_city", "city"));
        state.setText(prefs.getString("key_state", "state"));
           save.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String useremail=email.getText().toString();
                   String userarea=area.getText().toString();
                   String usercity=city.getText().toString();
                   String userstate=state.getText().toString();
                   String username=name.getText().toString();
                   String usermobile=mb.getText().toString();

                   SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                   SharedPreferences.Editor editor = pref.edit();
                   editor.putString("key_email", useremail);
                   editor.putString("key_area", userarea);
                   editor.putString("key_city", usercity);
                   editor.putString("key_state", userstate);
                   editor.commit();

                   Toast.makeText(getContext(),usermobile,Toast.LENGTH_LONG).show();

                   AccntBackground accntBackground= new AccntBackground(getContext());
                   accntBackground.execute(useremail,userarea,usercity,userstate,usermobile,username);

                 // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Menuu()).commit();

               }
           });
        return  view;
    }


}
/*public class profilefetch extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {
        String login_url= "http://192.168.43.221/registration.php";
        try {

            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
           /* OutputStream outputStream=httpURLConnection.getOutputStream();
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

    }*/
