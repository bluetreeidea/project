package com.example.androidtablayout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class Explore extends index {
  	  String prasi;
  	final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(this);
	private static final String TAG = null;
	private WebView mWebView;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos_layout);
	
        WebView webView = (WebView) findViewById(R.id.webview);
 //---      
        
        Intent i=getIntent();
        prasi=i.getStringExtra("userid");
     //   Toast.makeText(Profile.this, prasi, Toast.LENGTH_LONG).show();
        
    //------  
        WebSettings webSettings = webView.getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(0);
        webView.setBackgroundResource(R.drawable.smiley);
        webView.addJavascriptInterface( myJavaScriptInterface, "Explore_native");
     
        webView.loadUrl("file:///android_asset/www/explorepage.html?user_id="+prasi);
	}
	
	public class MyJavaScriptInterface {
		Context mContext;
		private Object mInstrumentation;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	/*    @JavascriptInterface
	    public void AndroidHTMLActivity(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, Category.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    */
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitycomedy(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, comedy.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        } 
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivityart(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, artandexp.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	     

	    @JavascriptInterface
	    public void AndroidHTMLActivitycats(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, cats.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitydogs(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, dogs.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivityfamily(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, family.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitybeauty(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, beauty.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivityfood(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, food.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivityhealth(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, health.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitynature(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, natural.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitymusic(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, music.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitynews(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, news.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivityspl(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, spl.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitysports(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, sports.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivityurban(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, urban.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	    @JavascriptInterface
	    public void AndroidHTMLActivitywired(String category)
        {
	    	
	    	
        	 Intent intent = new Intent(Explore.this, wired.class);
        	 intent.putExtra("category", category);
        	 intent.putExtra("userid", prasi);
			 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
             startActivity(intent,bndlanimation);
        }
	    
	 
	
	}

	}
	