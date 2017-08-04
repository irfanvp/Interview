package com.app.interview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String url="";
    TextView tv_load;

    private List<Student> studentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StudentAdapter sAdapter;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        if(isOnline(MainActivity.this)){
            studentList.clear();
            db = new DatabaseHandler(MainActivity.this);
            db.deletetable();
            load();
        }else{
            studentList.clear();
            db = new DatabaseHandler(MainActivity.this);

            studentList=db.getAllStudents();

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            sAdapter = new StudentAdapter(studentList,MainActivity.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(sAdapter);
        }



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
                                Student s=new Student();
                                s.setName(name);
                                s.setScore(score);
                                s.setLevel(level);
                                s.setImageurl(imageurl);
                                studentList.add(s);
                                db.addStudent(s);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }




                        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        sAdapter = new StudentAdapter(studentList,MainActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(sAdapter);


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

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }



}
