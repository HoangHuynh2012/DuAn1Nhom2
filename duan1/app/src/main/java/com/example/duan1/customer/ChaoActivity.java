package com.example.duan1.customer;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.customer.Onbroading.OnbroadingActivity;
import com.example.duan1.R;

public class ChaoActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao);
        ImageView imageView = findViewById(R.id.image);
        animationDrawable= (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new
                        Intent(getApplicationContext(), OnbroadingActivity.class));
                finish();
            }
        },2000);
    }
}