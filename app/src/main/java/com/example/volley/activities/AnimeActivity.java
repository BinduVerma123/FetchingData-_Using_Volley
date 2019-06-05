package com.example.volley.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.volley.R;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        //hide the default actionbar
        getSupportActionBar().hide();

        //Receive Data

        String name = getIntent().getExtras().getString("anime name");
        String description = getIntent().getExtras().getString("anime description");
        String studio = getIntent().getExtras().getString("anime studio");
        String category= getIntent().getExtras().getString("anime category");
        String rating = getIntent().getExtras().getString("anime rating");
        int nb_episode = getIntent().getExtras().getInt("anime nb_episode");
        String image_url = getIntent().getExtras().getString("anime img");

        //ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsinglayout_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name=findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_category = findViewById(R.id.aa_categories);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_rating);

        ImageView img = findViewById(R.id.aa_thumbnail);

        //setting values to each view

        tv_name.setText(name);
        tv_category.setText(category);
        tv_description.setText(description);
        tv_studio.setText(studio);
        tv_rating.setText(rating);

        //set image using Glide

        Glide.with(this).load(image_url).into(img);
    }
}
