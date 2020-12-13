package com.example.duan1.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.duan1.R;

public class WebView extends AppCompatActivity {
    android.webkit.WebView web;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        web = findViewById(R.id.webView);
        toolbar = findViewById(R.id.tbWebView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://www.thecoffeehouse.com/blogs/news/the-coffee-house-dong-hanh-cung-ban-go-green");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}