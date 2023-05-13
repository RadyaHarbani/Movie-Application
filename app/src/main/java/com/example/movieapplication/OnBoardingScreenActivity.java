package com.example.movieapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class OnBoardingScreenActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView backgroundSplashScreen,logoAplikasi;

    private static final int NUM_PAGES =3;
    private ViewPager viewPager;
    private OnBoardingPageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        lottieAnimationView = findViewById(R.id.lottie);
        backgroundSplashScreen = findViewById(R.id.background);
        logoAplikasi = findViewById(R.id.logo);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new OnBoardingPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        backgroundSplashScreen.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4100);
        logoAplikasi.animate().translationY(1400).setDuration(1000).setStartDelay(3000);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(IntroductyActivity.this, OnBoardingPage.class));
//                finish();
//            }
//        },5500);
    }

    private class OnBoardingPageAdapter extends FragmentStatePagerAdapter{

        public OnBoardingPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}