package com.example.volley.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.R;
import com.example.volley.adapters.RecyclerViewAdapter;
import com.example.volley.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL="https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime> lstanime;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstanime = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview_id);
        jsonRequest();
    }

    private void jsonRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
        for(int i=0;i<response.length();i++)
        {
           try{
               jsonObject =response.getJSONObject(i);
               Anime anime = new Anime();
               anime.setName(jsonObject.getString("name"));
               anime.setDescription(jsonObject.getString("description"));
               anime.setRating(jsonObject.getString("Rating"));
               anime.setCategorie(jsonObject.getString("categorie"));
               anime.setNb_episode(jsonObject.getInt("episode"));
               anime.setStudio(jsonObject.getString("studio"));
               anime.setImage_url(jsonObject.getString("img"));
               lstanime.add(anime);
           } catch (JSONException e) {
               e.printStackTrace();
           }
        }
        
        setupreccyclerview(lstanime);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

       requestQueue = Volley.newRequestQueue(MainActivity.this);
       requestQueue.add(request);
    }

    private void setupreccyclerview(List<Anime> lstanime) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstanime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
