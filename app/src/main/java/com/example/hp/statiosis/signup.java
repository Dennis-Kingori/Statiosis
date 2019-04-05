package com.example.hp.statiosis;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.statiosis.APIS.api;
import com.example.hp.statiosis.APIS.api.*;

import com.example.hp.statiosis.HANDLERS.RequestHandler;
import com.google.android.gms.common.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class signup extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    TextInputEditText firstname, secondname, username, password;
    Button signupbtn;
     ProgressBar progressBar;
    boolean isUpdating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        firstname = findViewById(R.id.firstnamee);
        secondname = findViewById(R.id.secondnamee);
        username = findViewById(R.id.usernamee);
        password = findViewById(R.id.passwordd);
        signupbtn = findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isUpdating){

                }
                else {
                    createuser();
                }
            }
        });
    }

    private void createuser(){

        String fname = firstname.getText().toString().trim();
        String sname = secondname.getText().toString().trim();
        String uname = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(sname) || TextUtils.isEmpty(uname) || TextUtils.isEmpty(pass)){
            firstname.setError("please fill all fields");
            firstname.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("first_name",fname);
        params.put("second_name",sname);
        params.put("username",uname);
        params.put("password",pass);

        //call api create user
        PerformNetworkRequest request = new PerformNetworkRequest(api.URL_CREATE_USER, params , CODE_POST_REQUEST);
        request.execute();

        isUpdating = false;



    }

    public void navopen(View view) {
        Intent navopen = new Intent(this, navigation.class);
        startActivity(navopen);
    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        String url;
        HashMap<String, String> params;

        int requestCode;

        public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
          //  progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //progressBar.setVisibility(View.GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")){
                    Toast.makeText(getApplicationContext(), object.getString("message"),Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids){
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.postRequest(url, params);

            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }

    }


}
