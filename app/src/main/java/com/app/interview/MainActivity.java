package com.app.interview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String url="";
    TextView tv_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        tv_load= (TextView) findViewById(R.id.tv_load);
        tv_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });

        //code test
    }

    public void load(){
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.show();

        url="https://openoffice.azurewebsites.net/api/shine";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("response", String.valueOf(response));
                        progressDialog.dismiss();
                        try{

                            for(int i=0;i<response.length();i++){

                                JSONObject student = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String name = student.getString("name");
                                String score = student.getString("score");
                                String level = student.getString("level");
                                String imageurl=student.getString("imageurl");

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                   progressDialog.dismiss();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);


    }
}
