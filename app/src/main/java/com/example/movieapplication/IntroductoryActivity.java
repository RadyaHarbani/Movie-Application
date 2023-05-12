package com.example.movieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView backgroundSplashScreen,logoAplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        lottieAnimationView = findViewById(R.id.lottie);
        backgroundSplashScreen = findViewById(R.id.background);
        logoAplikasi = findViewById(R.id.logo);

        backgroundSplashScreen.animate().translationY(-2500).setDuration(800).setStartDelay(4700);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4700);
        logoAplikasi.animate().translationY(1400).setDuration(1000).setStartDelay(4700);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(IntroductyActivity.this, OnBoardingPage.class));
//                finish();
//            }
//        },5500);
    }
}