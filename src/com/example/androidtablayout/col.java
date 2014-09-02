package com.example.androidtablayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class col extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);   
        WebView web=(WebView)findViewById(R.id.webview);
        
       
    	WebSettings settings=web.getSettings();
    	web.getSettings().setJavaScriptEnabled(true);
    	web.getSettings().setDomStorageEnabled(true);
    	web.loadUrl("file:///android_asset/www/commentscreen.html");  
    	web.setWebChromeClient(new WebChromeClient());
    }

}
