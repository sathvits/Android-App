package com.example.attendancedemo;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username,password;
    String uname,pwd;
    Webservice ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        ws = new Webservice();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname= username.getText().toString();
                pwd = password.getText().toString();

                //PostData(uname,pwd);
                invokeWS(uname,pwd);

               /* if(uname.equals("Admin")&& pwd.equals("admin")){
                    //Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
                    Intent detailsintent=new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(detailsintent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Not valid user",Toast.LENGTH_LONG).show();
                }*/


            }
        });


    }

    private void invokeWS(String username,String password) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams param = new RequestParams();
        param.put("username", username);
        param.put("password", password);
///////////////////////////        client.get(ws.URL+ws.LOGIN, param, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //Toast.makeText(getApplicationContext(),"Success "+statusCode,Toast.LENGTH_LONG).show();
                /*Intent detailsintent=new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(detailsintent);*/
                try {
                    JSONObject jsonObj = new JSONObject(response.toString());

                    System.out.println("***** Data" + jsonObj.getString("response"));
                    jsonObj = new JSONObject(jsonObj.getString("response"));
                    System.out.println("***** Status" + jsonObj.getString("status"));
                    System.out.println("***** UserId" + jsonObj.getString("userid"));
                    System.out.println("***** Message" + jsonObj.getString("message"));

                    if(jsonObj.getString("status").equals("0")){
                        Toast.makeText(getApplicationContext(),""+jsonObj.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),""+jsonObj.getString("message"),Toast.LENGTH_LONG).show();
                        Intent detailsintent=new Intent(MainActivity.this, DetailsActivity.class);
                        startActivity(detailsintent);
                    }



//                    for (int i = 0; i < jsonObj.length(); i++) {
//                        try {
//
//                            String status = jsonObj.getString("status");
//                            System.out.println("status " + status);
//
//
//                        } catch (Exception e) {
//                            System.out.println("Exception " + e.getMessage());
//                        }
//                    }
                }
                    catch(Exception e){
                        System.out.println("Exception " + e.getMessage());
                    }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error"+statusCode,Toast.LENGTH_LONG).show();
            }
        });
    }
}
