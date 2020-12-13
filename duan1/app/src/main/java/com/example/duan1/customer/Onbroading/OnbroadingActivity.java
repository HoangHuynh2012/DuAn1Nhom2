package com.example.duan1.customer.Onbroading;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.duan1.R;
import com.example.duan1.customer.TrangChuActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnbroadingActivity extends AppCompatActivity {
    public static ViewPager viewPager;
    SlideViewPagerAdapter adapter;
    TabLayout tabLayout;
    Button btnNext,btnGetstarted;
    Animation animation;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onbroading);


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tab_indication);
        btnNext = findViewById(R.id.btn_next);
        btnGetstarted = findViewById(R.id.btn_getstarted);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);


        List<ScreenItem> list =new ArrayList<>();
        list.add(new ScreenItem("Đa dạng thể loại","Cuộc đời của chúng ta cũng như một tách cà phê. Nhưng đôi khi vì quá chạy "+
                " theo những cái tách mà chúng ta bỏ lỡ cơ hội thưởng thức cà phê. ", R.drawable.c1));
        list.add(new ScreenItem("Hương vị đậm đà","Cà phê thì phải đen như địa ngục, đắng như tử thần và ngọt ngào tựa tình yêu.", R.drawable.c2));
        list.add(new ScreenItem("Giao hàng nhanh chóng","", R.drawable.c3));

        viewPager = findViewById(R.id.pagerIntroSlider );
        adapter= new SlideViewPagerAdapter(this,list);
        viewPager.setAdapter(adapter);

        //set up tablayout vs viewpager
        tabLayout.setupWithViewPager(viewPager);

        //button setOnclick
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = viewPager.getCurrentItem();
                if(position < list.size()){
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if(position == list.size()-1){
                    loadLastScreen();
                }
            }
        });
        btnGetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
                overridePendingTransition(0,0);
                savePrefsData();
                finish();
            }

        });


    }

    private void savePrefsData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpen",true);
        editor.commit();
    }
    private void loadLastScreen(){
        btnNext.setVisibility(View.INVISIBLE);
        btnGetstarted.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        btnGetstarted.setAnimation(animation);
    }
}