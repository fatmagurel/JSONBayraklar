package com.example.arslanzeliha.jsonbayrak;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
           private final static String DATA_URL = "https://next.json-generator.com/api/json/get/VJnHwY0rH";
           ListView lvUlkeler;
           ImageView ivBayraklar;
           String[] imageURLs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvUlkeler=(ListView) findViewById(R.id.lv_ulkeler);
        ivBayraklar=(ImageView) findViewById(R.id.iv_bayraklar);

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,DATA_URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("Bayraklar");
                            int length=jsonArray.length();
                            imageURLs=new String[length];
                            String [] listData=new String[length];
                            for(int i=0;i<length;i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                String ulkeler=o.getString("Ulkeler");
                                String baskent=o.getString("Baskent");
                                String nufus=o.getString("Nufus");
                                imageURLs[i]=o.getString("ImageURL");
                                listData[i]=ulkeler+" , "+baskent+" , "+nufus;
                            }
                            ArrayAdapter<String>adapter=new ArrayAdapter <>(getApplicationContext(),android.R.layout.simple_list_item_1,listData);
                            lvUlkeler.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.getMessage());
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        lvUlkeler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Picasso.get().load(imageURLs[position]).into(ivBayraklar);
            }
        });

            }
        }
