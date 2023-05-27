package com.example.movieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMoviePage extends AppCompatActivity {

    private TextView tvDetailTitle;
    private TextView tvDetailDescription;
    private ImageView imgDetailMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_page);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        imgDetailMovie = findViewById(R.id.imgDetailMovie);

        Intent intent = getIntent();
        if (intent.hasExtra("myteam")){
            ModelMovie modelMovie = intent.getParcelableExtra("myteam");
            tvDetailTitle.setText(modelMovie.getOriginal_title());
            tvDetailDescription.setText(modelMovie.getOverview());

            String backdropUrl = "https://image.tmdb.org/t/p/w500" + modelMovie.getBackdrop_path();
            Glide.with(DetailMoviePage.this).load(backdropUrl).into(imgDetailMovie);
        }
    }
}