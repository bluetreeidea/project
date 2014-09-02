package com.example.androidtablayout;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class camera extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_layout);
        WebView web=(WebView)findViewById(R.id.webview);
    	WebSettings settings=web.getSettings();
    	web.getSettings().setJavaScriptEnabled(true);
    	web.loadUrl("file:///android_asset/www/video.html");
    	//web.loadUrl("https://www.facebook.com/");
    }
}
