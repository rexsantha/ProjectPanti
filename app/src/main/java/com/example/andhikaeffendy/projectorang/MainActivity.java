package com.example.andhikaeffendy.projectorang;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.andhikaeffendy.projectorang.AccountActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlidePager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*LinearLayout panti = (LinearLayout) findViewById(R.id.panti_btn);
        panti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaftarPantiActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout donatur = (LinearLayout) findViewById(R.id.btn_donatur);
        donatur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DonasiActivity.class);
                startActivity(intent);
            }
        });*/

        mSlidePager = (ViewPager) findViewById(R.id.slider);

        SliderAdapter sliderAdapter = new SliderAdapter(this);

        mSlidePager.setAdapter(sliderAdapter);

        getSupportActionBar().show();


    }

}
